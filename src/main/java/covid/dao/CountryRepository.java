package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;
import covid.dto.InfoCountry;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring 
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT name_Country, total_Cases "
            + "FROM Country "
            + "WHERE Continent_Name_Continent = 'Europe' "
            , nativeQuery = true)
    List<Object> getEuropeanCountries();
    
    @Query(value = "SELECT code_Country, name_Country, total_Cases, total_Deaths, "
            + "icu_Patients, hosp_Patients, total_Tests, total_Vaccinations, "
            + "fully_Vaccinated, stringency_Index, population, gdp "
            + "FROM Country "
            + "WHERE name_Country = :nameCountry "
            , nativeQuery = true)
    InfoCountry getCountry(@Param("nameCountry") String nameCountry);
    
}
