package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Continent;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import covid.dto.InfoContinent;
import covid.dto.InfoWorld;

// This will be AUTO IMPLEMENTED by Spring 

public interface ContinentRepository extends JpaRepository<Continent, String> {
    
    // -- Nouvel ajout
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
