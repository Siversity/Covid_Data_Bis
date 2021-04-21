package covid.apicontroller;

import covid.dao.ContinentRepository;
import covid.dao.CountryRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import covid.dto.InfoContinent;

@Service
@RequestMapping(path = "/api/continent")
public class ContinentApiController {

    @Autowired
    CountryRepository countryDAO;

    // API renvoyant les infos actualisées d'un Continent
    @GetMapping(path = "getContinent", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InfoContinent getInfosContinent(@RequestParam(required = true) final String nameContinent) {
        LocalDate today = LocalDate.now().minusDays(2);
        return countryDAO.getInfosContinentByName(nameContinent, today);
    }

}
