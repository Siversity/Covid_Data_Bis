package covid.apicontroller;

import covid.dao.CountryRepository;
import covid.dao.InfoDailyCountryRepository;
import covid.dto.InfoCountry;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequestMapping(path = "/api/country")
public class CountryApiController {

    // DAO
    @Autowired
    private CountryRepository countryDAO;
    @Autowired
    private InfoDailyCountryRepository infoDailyDAO;
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // AFFICHAGE INFOS NEW CASES & DEATHS : GEO CHART //
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    // API utilisée par GeoChart Google rencoyant le nom d'un Country et le nombre de nouveaux cas récent
    @GetMapping(path = "newcases", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getNewCases() {
        LocalDate today = LocalDate.now().minusDays(2);
        return infoDailyDAO.getNewCases(today);
    }

    // API utilisée par GeoChart Google rencoyant le nom d'un Country et le nombre de nouveaux morts récent
    @GetMapping(path = "newdeaths", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getNewDeaths() {
        LocalDate today = LocalDate.now().minusDays(2);
        return infoDailyDAO.getNewDeaths(today);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // AFFICHAGE TOTAL CASES & DEATHS : GEOCHART //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // API utilisée par GeoChart Google rencoyant le nom d'un Country et le nombre de nouveaux cas totaux
    @GetMapping(path = "totalcases", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getTotalCases() {
        return countryDAO.getTotalCases();
    }
    
    // API utilisée par GeoChart Google rencoyant le nom d'un Country et le nombre de nouveaux morts totaux
    @GetMapping(path = "totaldeaths", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Object> getTotalDeaths() {
        return countryDAO.getTotalDeaths();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // AFFICHAGE INFOS CLASSIQUES : AJAX //
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    // API utilisée pour afficher les infos actualisées d'un Country
    @GetMapping(path = "getCountry", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    InfoCountry getCountryInfos(@RequestParam(required = true) final String nameCountry) {
        LocalDate today = LocalDate.now().minusDays(2);
        return countryDAO.getInfoCountryByName(nameCountry, today);
    }
    
    

}
