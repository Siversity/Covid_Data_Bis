package covid.apicontroller;

import covid.dao.InfoDailyCountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequestMapping(path = "/api/infoDaily")
public class InfoDailyCountryApiController {

    // DAO
    @Autowired
    InfoDailyCountryRepository infoDailyDAO;
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // AFFICHAGE INFOS TOTAL CASES & DEATHS : LINE CHART//
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    // API permettant d'afficher l'évolution des stats du Country en renvoyant une liste des infos journalières
    @GetMapping(path = "country/totalstats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Object> getAllDailyTotalStatsCountry(@RequestParam(required = true) final String nameCountry) {
        return infoDailyDAO.getAllDailyTotalStatsByCountry(nameCountry);
    }

    // API permettant d'afficher l'évolution des stats du Continent en renvoyant une liste des infos journalières
    @GetMapping(path = "continent/totalstats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Object> getAllDailyTotalStatsContinent(@RequestParam(required = true) final String nameContinent) {
        return infoDailyDAO.getAllDailyTotalStatsByContinent(nameContinent);
    }

    // API permettant d'afficher l'évolution des stats de World en renvoyant une liste des infos journalières
    @GetMapping(path = "world/totalstats", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getAllDailyTotalStatsWorld() {
        return infoDailyDAO.getAllDailyTotalStatsWorld();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // AFFICHAGE INFOS NEW CASES & DEATHS : LINE CHART //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // API permettant d'afficher l'évolution des stats du Country en renvoyant une liste des infos journalières
    @GetMapping(path = "country/newstats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Object> getAllDailyNewStatsCountry(@RequestParam(required = true) final String nameCountry) {
        return infoDailyDAO.getAllDailyNewStatsByCountry(nameCountry);
    }

    // API permettant d'afficher l'évolution des stats du Continent en renvoyant une liste des infos journalières
    @GetMapping(path = "continent/newstats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Object> getAllDailyStatsContinent(@RequestParam(required = true) final String nameContinent) {
        return infoDailyDAO.getAllDailyNewStatsByContinent(nameContinent);
    }

    // API permettant d'afficher l'évolution des stats de World en renvoyant une liste des infos journalières
    @GetMapping(path = "world/newstats", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getAllDailyNewStatsWorld() {
        return infoDailyDAO.getAllDailyNewStatsWorld();
    }

}
