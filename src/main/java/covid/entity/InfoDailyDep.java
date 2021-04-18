package covid.entity;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class InfoDailyDep {
    
    // Attributs
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idInfoCountry;
    
    @Column
    @NonNull
    private Date date;
    
    @Column
    private Integer nbrHosp;
    
    @Column
    private Integer nbrRea;
    
    @Column
    private Integer nbrRad;
    
    @Column
    private Integer nbrDC;
    
    // Relations
    @ManyToOne
    private Departement departmentInformed;
    
}
