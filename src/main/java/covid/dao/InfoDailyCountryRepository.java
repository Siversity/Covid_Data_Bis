package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.InfoDailyCountry;

// This will be AUTO IMPLEMENTED by Spring 

public interface InfoDailyCountryRepository extends JpaRepository<InfoDailyCountry, Integer> {
    
}
