package covid.entity;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class InfoDailyCountry {
    
    // Attributs
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idInfoCountry;
    
    @Column
    @NonNull
    private Date date;
    
    @Column
    private Integer newCases;
    
    @Column
    private Integer newDeaths;
    
    @Column
    private Integer positiveRate;
    
    @Column
    private Integer newTests;
    
    @Column
    private Integer newVaccinations;
    
    /*
    // Relations
    @ManyToOne
    @NonNull
    private Country countryInformed;
    */
}