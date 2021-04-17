package covid.dao;

import covid.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring 
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT * FROM Country WHERE Continent_Name_Continent = 'Europe' ", nativeQuery = true)
    List<Country> getEuropeanCountries();

}
