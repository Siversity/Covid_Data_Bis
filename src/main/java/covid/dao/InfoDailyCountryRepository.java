package covid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import covid.entity.InfoDailyCountry;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InfoDailyCountryRepository extends JpaRepository<InfoDailyCountry, Integer> {

    // Requête permettant de récupérer l'ID à partir de la date et du codeCountry
    @Query(value = "SELECT Id_Info_Country AS id FROM Info_Daily_Country "
            + "WHERE Country_Informed_Code_Country = :codeCountry "
            + "AND Date = :date ",
            nativeQuery = true)
    Integer getIdInfoCountryByCountryInformedCodeCountryAndDate(@Param("codeCountry") String codeCountry, @Param("date") LocalDate date);

    // Requête permettant de récupérer le nombre de nouveaux cas en fonction de la date
    @Query(value = "SELECT Country.name_Country AS name, Info_Daily_Country.new_Cases AS ncases "
            + "FROM Info_Daily_Country "
            + "INNER JOIN Country "
            + "ON Info_Daily_Country.Country_Informed_Code_Country=Country.Code_Country "
            + "WHERE Info_Daily_Country.date = :date ",
            nativeQuery = true)
    List<Object> getNewCases(@Param("date") LocalDate date);

    // Requête permettant de récupérer les données journalières d'un Country
    @Query(value = "SELECT date, Info_Daily_Country.total_Cases AS tcases, Info_Daily_Country.total_Deaths AS tdeaths "
            + "FROM Info_Daily_Country "
            + "INNER JOIN Country "
            + "ON Info_Daily_Country.Country_Informed_Code_Country=Country.Code_Country "
            + "WHERE Country.name_Country = :nameCountry ",
            nativeQuery = true)
    List<Object> getAllDailyStatsByCountry(@Param("nameCountry") String nameCountry);

    // Requête permettant de récupérer les données journalières d'un Continent
    @Query(value = "SELECT date, SUM(Info_Daily_Country.total_Cases) AS tcases, SUM(Info_Daily_Country.total_Deaths) AS tdeaths "
            + "FROM Info_Daily_Country "
            + "INNER JOIN Country "
            + "ON Info_Daily_Country.Country_Informed_Code_Country=Country.Code_Country "
            + "WHERE Country.Continent_Name_Continent = :nameContinent "
            + "GROUP BY date ",
            nativeQuery = true)
    List<Object> getAllDailyStatsByContinent(@Param("nameContinent") String nameContinent);
    
    // Requête permettant de récupérer les données journalières de World
    @Query(value = "SELECT date, SUM(Info_Daily_Country.total_Cases) AS tcases, SUM(Info_Daily_Country.total_Deaths) AS tdeaths "
            + "FROM Info_Daily_Country "
            + "INNER JOIN Country "
            + "ON Info_Daily_Country.Country_Informed_Code_Country=Country.Code_Country "
            + "GROUP BY date ",
            nativeQuery = true)
    List<Object> getAllDailyStatsWorld();
    
}
