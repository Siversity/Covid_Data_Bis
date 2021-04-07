package covid.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Country {
    
    // Attributs
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idCountry;

    @Column(unique=true)
    @NonNull
    private String nameCountry;
    
    @Column
    private Integer totalCases;
    
    @Column
    private Integer totalDeaths;
    
    @Column
    private Integer icuPatients;
    
    @Column
    private Integer hospPatients;
    
    @Column
    private Integer totalTest;
    
    @Column
    private Integer totalVaccinations;
    
    @Column
    private Integer fullyVaccineted;
    
    @Column
    private Integer StringencyIndex;
    
    @Column
    private float population;
    
    @Column
    private Integer gdp;
    
    // Relations
    @ManyToOne
    @NonNull
    private Continent zone;
    
    @OneToMany(mappedBy = "countryArea")
    private List<Area> listeArea = new LinkedList<>();
    
    @OneToMany(mappedBy = "countryInformed")
    private List<InfoDailyCountry> listInfoDailyCountry = new LinkedList<>();
    
}
