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
    @Query(value = "SELECT name_Country, total_Cases "
            + "FROM Country "
            + "WHERE Continent_Name_Continent LIKE %:nameContinent% "
            , nativeQuery = true)
    List<Object> getCountriesByContinent(@Param("nameContinent") String nameContinent);
    
    // Requête permettant de récupérer les infos d'un Country en fonction de la date
    @Query(value = "SELECT c.code_Country, c.name_Country, c.total_Cases, c.total_Deaths, "
            + "c.icu_Patients, c.hosp_Patients, c.total_Tests, c.total_Vaccinations, "
            + "c.fully_Vaccinated, c.stringency_Index, c.population, c.gdp, "
            + "i.new_Cases, i.new_Deaths, i.new_Vaccinations "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_Country=i.country_informed_code_country "
            + "WHERE c.name_Country = :nameCountry "
            + "AND i.date = :date "
            , nativeQuery = true)
    InfoCountry getInfoCountryByName(@Param("nameCountry") String nameCountry, @Param("date") LocalDate date);
    
    // Fonction permettant de récupérer les infos d'un Continent en fonction de la date et de nameContinent
    @Query(value = "SELECT c.Continent_Name_Continent, SUM(c.total_Cases) AS total_Cases, "
            + "SUM(c.total_Deaths) AS total_Deaths, SUM(c.icu_Patients) AS icu_Patients, "
            + "SUM(c.hosp_Patients) AS hosp_Patients, SUM(c.total_Tests) AS total_Tests, "
            + "SUM(c.total_Vaccinations) AS total_Vaccinations, SUM(c.fully_Vaccinated) AS fully_Vaccinated, "
            + "SUM(c.population) AS population, SUM(i.new_Cases) AS new_Cases, SUM(i.new_Deaths) AS new_Deaths, "
            + "SUM(i.new_Vaccinations) AS new_Vaccinations "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_country=i.country_informed_code_country "
            + "WHERE c.Continent_Name_Continent = :nameContinent "
            + "AND i.date = :date "
            + "GROUP BY Continent_Name_Continent "
            , nativeQuery = true)
    InfoContinent getInfosContinentByName(@Param("nameContinent") String nameContinent, @Param("date") LocalDate date);
   
    // Fonction permettant de récupérer les infos de World en fonction de la date
   @Query(value = "SELECT SUM(c.total_Cases) AS total_Cases, "
            + "SUM(c.total_Deaths) AS total_Deaths, SUM(c.icu_Patients) AS icu_Patients, "
            + "SUM(c.hosp_Patients) AS hosp_Patients, SUM(c.total_Tests) AS total_Tests, "
            + "SUM(c.total_Vaccinations) AS total_Vaccinations, SUM(c.fully_Vaccinated) AS fully_Vaccinated, "
            + "SUM(c.population) AS population, SUM(i.new_Cases) AS new_Cases, SUM(i.new_Deaths) AS new_Deaths, "
            + "SUM(i.new_Vaccinations) AS new_Vaccinations "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_country=i.country_informed_code_country "
            + "WHERE i.date = :date "
            , nativeQuery = true)
    InfoWorld getInfosWorld(@Param("date") LocalDate date);
    
}
