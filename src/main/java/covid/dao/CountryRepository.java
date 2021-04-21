package covid.dao;

import covid.dto.InfoContinent;
import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;
import covid.dto.InfoCountry;
import covid.dto.InfoWorld;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Requête permettant de récupérer la liste des Country en fonction de leur Continent
    @Query(value = "SELECT name_Country AS name, total_Cases AS cases "
            + "FROM Country "
            + "WHERE Continent_Name_Continent LIKE %:nameContinent% "
            , nativeQuery = true)
    List<Object> getCountriesByContinent(@Param("nameContinent") String nameContinent);
    
    // Requête permettant de récupérer les infos d'un Country en fonction de la date
    @Query(value = "SELECT c.code_Country AS code, c.name_Country AS name, c.total_Cases AS tcases, c.total_Deaths AS tdeaths, "
            + "c.icu_Patients AS icu, c.hosp_Patients AS hosp, c.total_Tests AS ttest, c.total_Vaccinations AS tvaccinations, "
            + "c.fully_Vaccinated AS fvaccinated, c.stringency_Index AS si, c.population AS pop, c.gdp AS gdp, "
            + "i.new_Cases AS ncases, i.new_Deaths AS ndeaths, i.new_Vaccinations AS nvaccinations "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_Country=i.country_informed_code_country "
            + "WHERE c.name_Country = :nameCountry "
            + "AND i.date = :date "
            , nativeQuery = true)
    InfoCountry getInfoCountryByName(@Param("nameCountry") String nameCountry, @Param("date") LocalDate date);
    
    // Fonction permettant de récupérer les infos d'un Continent en fonction de la date et de nameContinent
    @Query(value = "SELECT c.Continent_Name_Continent AS name, SUM(c.total_Cases) AS tcases, "
            + "SUM(c.total_Deaths) AS tdeaths, SUM(c.icu_Patients) AS icu, "
            + "SUM(c.hosp_Patients) AS hosp, SUM(c.total_Tests) AS ttests, "
            + "SUM(c.total_Vaccinations) AS tvaccinations, SUM(c.fully_Vaccinated) AS fvaccinated, "
            + "SUM(c.population) AS pop, SUM(i.new_Cases) AS ncases, SUM(i.new_Deaths) AS ndeaths, "
            + "SUM(i.new_Vaccinations) AS nvaccinations "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_country=i.country_informed_code_country "
            + "WHERE c.Continent_Name_Continent = :nameContinent "
            + "AND i.date = :date "
            + "GROUP BY Continent_Name_Continent "
            , nativeQuery = true)
    InfoContinent getInfosContinentByName(@Param("nameContinent") String nameContinent, @Param("date") LocalDate date);
   
    ///////////////////////////////////////
    // API GOOGLE GEOCHART //
    ///////////////////////////////////////
    // Fonction permettant de récupérer les infos de World en fonction de la date
   @Query(value = "SELECT SUM(c.total_Cases) AS tcases, "
            + "SUM(c.total_Deaths) AS tdeaths, SUM(c.icu_Patients) AS icu, "
            + "SUM(c.hosp_Patients) AS hosp, SUM(c.total_Tests) AS ttests, "
            + "SUM(c.total_Vaccinations) AS tvaccinations, SUM(c.fully_Vaccinated) AS fvaccinated, "
            + "SUM(c.population) AS pop, SUM(i.new_Cases) AS ncases, SUM(i.new_Deaths) AS ndeaths, "
            + "SUM(i.new_Vaccinations) AS nvaccinations "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_country=i.country_informed_code_country "
            + "WHERE i.date = :date "
            , nativeQuery = true)
    InfoWorld getInfosWorld(@Param("date") LocalDate date);
    
    // Requête permettant de récupérer le nombre de cas totaux
    @Query(value = "SELECT name_Country AS name, total_Deaths AS tdeaths "
            + "FROM Country "
            , nativeQuery = true)
    List<Object> getTotalDeaths();
    
    // Requête permettant de récupérer le nombre de cas totaux
    @Query(value = "SELECT name_Country AS name, total_Cases AS tcases "
            + "FROM Country "
            , nativeQuery = true)
    List<Object> getTotalCases();
    
}
