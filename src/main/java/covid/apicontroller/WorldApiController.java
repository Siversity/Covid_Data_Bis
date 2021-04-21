package covid.apicontroller;

import covid.dao.ContinentRepository;
import covid.dao.CountryRepository;
import covid.dao.InfoDailyCountryRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import covid.dto.InfoWorld;

@Service
@RequestMapping(path = "/api/world")
public class WorldApiController {

    @Autowired
    CountryRepository countryDAO;

    @GetMapping(path = "getWorld", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InfoWorld getInfosWorld() {
        LocalDate today = LocalDate.now().minusDays(2);
        return countryDAO.getInfosWorld(today);
    }

}
