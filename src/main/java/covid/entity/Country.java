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
public class Country {

    // Attributs
    @Column(unique = true)
    @NonNull
    @Id
    private String codeCountry;

    @Column(unique = true)
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
    private float fullyVaccinated;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "countryInformed")
    private List<InfoDailyCountry> infos = new LinkedList<>();

    // Lien Country Continent
    public void addContinent(Continent continent) {
        this.continent = continent;
    }
    
}
