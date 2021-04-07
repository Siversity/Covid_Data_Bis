package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Region;

// This will be AUTO IMPLEMENTED by Spring

public interface AreaRepository extends JpaRepository<Region, Integer>{
    
}
