package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Department;

// This will be AUTO IMPLEMENTED by Spring 

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}
