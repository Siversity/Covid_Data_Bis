package covid.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Continent {

    // Attributs
    @Column(unique = true)
    @NonNull
    @Id
    private String nameContinent;

    // Relations
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "continent", cascade = CascadeType.ALL)
    List<Country> countries = new LinkedList<Country>();

    // Lien entre Country - Continent
    public void addCountry(Country country) {
        this.countries.add(country);
        country.setContinent(this);
    }

}
