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
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idContinent;

    @Column(unique=true)
    @NonNull
    private String nameContinent;
    
    
    // Relations
    @OneToMany(mappedBy = "continent")
    List<Country> countries = new LinkedList<>();
}
