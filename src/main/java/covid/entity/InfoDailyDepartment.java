package covid.entity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class InfoDailyDepartment {
    
    // Attributs
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idInfoCountry;
    
    @Column
    @NonNull
    private LocalDate date;
    
    @Column
    private Integer nbrHospitalized;
    
    @Column
    private Integer newReanimation;
    
    @Column
    private Integer newCured;
    
    @Column
    private Integer cumuledDeaths;
    
    // Relations
    @ManyToOne
    private Department departmentInformed;
    
}
