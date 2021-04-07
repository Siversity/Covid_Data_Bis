package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.InfoDailyDepartment;

// This will be AUTO IMPLEMENTED by Spring 

public interface InfoDailyDepartmentRepository extends JpaRepository<InfoDailyDepartment, Integer> {
    
}
