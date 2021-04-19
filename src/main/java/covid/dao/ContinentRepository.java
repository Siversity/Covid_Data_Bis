package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Continent;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring 

public interface ContinentRepository extends JpaRepository<Continent, String> {
    
    // -- Nouvel ajout
    @Query(value = "SELECT Continent_Name_Continent, SUM(total_Cases), SUM(total_Deaths) "
            + "FROM Country "
            + "WHERE Country.Continent_Name_Continent = :nameContinent "
            + "GROUP BY Continent_Name_Continent "
            , nativeQuery = true)
    Object getInfosContinentByName(@Param("nameContinent") String nameContinent);
    
   
}
