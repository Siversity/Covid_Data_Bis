package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.InfoDailyDep;

// This will be AUTO IMPLEMENTED by Spring 

public interface InfoDailyDepartmentRepository extends JpaRepository<InfoDailyDep, Integer> {
    
}
