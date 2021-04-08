package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, String> {
    
}
