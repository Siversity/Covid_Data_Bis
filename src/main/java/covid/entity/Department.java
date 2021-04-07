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
public class Department {
    
    // Attributs
    @Id
    @NonNull
    private Integer idDepartment;
    
    @Column
    private String nameDepartment;
    
    // Relations
    @ManyToOne
    private Area areaDepartment;
    
    @OneToMany(mappedBy = "departmentInformed")
    private List<InfoDailyDepartment> listInfoDailyDepartment = new LinkedList<>();
    
}