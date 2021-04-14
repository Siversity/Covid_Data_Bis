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
    @Column (unique=true)
    @NonNull
    @Id
    private String codeCountry;
    
    @Column(unique=true)
    @NonNull
    private String nameCountry;
    
    @Column
    private float totalCases;
    
    @Column
    private float totalDeaths;
    
    @Column
    private float icuPatients;
    
    @Column
    private float hospPatients;
    
    @Column
    private float totalTests;
    
    @Column
    private float totalVaccinations;
    
    @Column
    private float fullyVaccineted;
    
    @Column
    private float stringencyIndex;
    
    @Column
    private float population;
    
    @Column
    private float gdp;


    // Relations
    @ManyToOne
    @NonNull
    private Continent continent;
    
    /*
    @OneToMany(mappedBy = "paysFr")
    private List<Region> regionsFr = new LinkedList<>();
*/
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "countryInformed")
    private List<InfoDailyCountry> infos = new LinkedList<>();
    
    public void addContinent(Continent continent) {
        this.continent = continent;
    }
}
