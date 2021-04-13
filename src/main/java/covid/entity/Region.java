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
public class Region {
    
    // Attributs
    @Column(unique=true)
    @NonNull
    @Id
    private String nomRegion;
    
    /*
    // Relations
    @ManyToOne
    @NonNull
    private Country paysFr;
    */
    
    @OneToMany(mappedBy = "region")
    private List<Departement> departements = new LinkedList<>();
    
}
