package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.Continent;

public interface ContinentRepository extends JpaRepository<Continent, String> {
    
    
}
