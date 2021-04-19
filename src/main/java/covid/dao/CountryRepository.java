package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;
import covid.dto.InfoCountry;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring 
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT name_Country, total_Cases "
            + "FROM Country "
            + "WHERE Continent_Name_Continent LIKE %:nameContinent% "
            , nativeQuery = true)
    List<Object> getCountriesByContinent(@Param("nameContinent") String nameContinent);
    
    @Query(value = "SELECT c.code_Country, c.name_Country, c.total_Cases, c.total_Deaths, "
            + "c.icu_Patients, c.hosp_Patients, c.total_Tests, c.total_Vaccinations, "
            + "c.fully_Vaccinated, c.stringency_Index, c.population, c.gdp, "
            + "i.new_Cases, i.new_Deaths "
            + "FROM Country c "
            + "INNER JOIN Info_Daily_Country i "
            + "ON c.code_Country=i.country_informed_code_country "
            + "WHERE c.name_Country = :nameCountry "
            + "AND i.date = :date "
            , nativeQuery = true)
    InfoCountry getCountryByName(@Param("nameCountry") String nameCountry, @Param("date") LocalDate date);
    
}
