package covid.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class InfoDailyCountry {

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInfoCountry;

    @Column
    @NonNull
    private LocalDate date;

    @Column
    private float totalCases;

    @Column
    private float totalDeaths;

    @Column
    private float newCases;

    @Column
    private float newDeaths;

    @Column
    private float positiveRate;

    @Column
    private float newTests;

    @Column
    private float newVaccinations;

    // Relations
    @ManyToOne
    @NonNull
    private Country countryInformed;

}
