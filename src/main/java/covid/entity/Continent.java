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
public class Continent {
    
    // Attributs
    @Column (unique=true)
    @NonNull
    @Id
    private String nameContinent;
    
    // Relations
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "continent")
    List<Country> countries = new LinkedList<Country>();
    
    // Ajout de Country
    public void addCountry(Country country) {
        this.countries.add(country);
    }
}
