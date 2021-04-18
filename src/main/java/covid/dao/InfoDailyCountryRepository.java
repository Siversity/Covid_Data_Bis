package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.InfoDailyCountry;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring 

public interface InfoDailyCountryRepository extends JpaRepository<InfoDailyCountry, Integer> {
    
    @Query(value = "SELECT Id_Info_Country FROM Info_Daily_Country WHERE Country_Informed_Code_Country = :codeCountry AND Date = :date ", nativeQuery = true)
    Integer getIdInfoCountryByCountryInformedCodeCountryAndDate(@Param("codeCountry") String codeCountry, @Param("date") LocalDate date);
}
