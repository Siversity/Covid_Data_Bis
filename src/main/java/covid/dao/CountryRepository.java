package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring 

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
}
