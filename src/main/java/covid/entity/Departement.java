package covid.entity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @ToString
@Entity // Une entité JPA
public class Departement {
    
    // Attributs
    @Column (unique = true)
    @NonNull
    private String nomDepartement;
    
    @Column (unique = true)
    @NonNull
    @Id
    private int numDepartement;
    
    
    // Relations
    @ManyToOne
    private Region region;
    
    @OneToMany(mappedBy = "departmentInformed")
    private List<InfoDailyDep> infos = new LinkedList<>();
    
}