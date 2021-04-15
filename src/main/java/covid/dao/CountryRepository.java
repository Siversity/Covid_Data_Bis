package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring 
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /*
    @Query("SELECT * AS countries "
            + "FROM Country c "
            + "WHERE c.continent.nameContinent = :nameContinent ")
    public List<Country> findByContinent(String nameContinent);
    */

}
