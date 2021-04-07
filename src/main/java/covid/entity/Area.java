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
public class Area {
    
    // Attributs
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idArea;

    @Column(unique=true)
    @NonNull
    private String nameArea;
    
    
    // Relations
    @ManyToOne
    private Country countryArea;
    
    @OneToMany(mappedBy = "areaDepartment")
    private List<Department> listDepartment = new LinkedList<>();
    
}
