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

    @Autowired
    InfoDailyCountryRepository infoDailyDAO;

    // API permettant d'afficher l'évolution des stats du Country en renvoyant une liste des infos journalières
    @GetMapping(path = "country/stats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Object> getAllDailyStatsCountry(@RequestParam(required = true) final String nameCountry) {
        return infoDailyDAO.getAllDailyStatsByCountry(nameCountry);
    }

    // API permettant d'afficher l'évolution des stats du Continent en renvoyant une liste des infos journalières
    @GetMapping(path = "continent/stats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Object> getAllDailyStatsContinent(@RequestParam(required = true) final String nameContinent) {
        return infoDailyDAO.getAllDailyStatsByContinent(nameContinent);
    }

    // API permettant d'afficher l'évolution des stats de World en renvoyant une liste des infos journalières
    @GetMapping(path = "world/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getAllDailyStatsWorld() {
        return infoDailyDAO.getAllDailyStatsWorld();
    }

}
