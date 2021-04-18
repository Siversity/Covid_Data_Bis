package covid.controller;

import covid.dao.ContinentRepository;
import covid.dao.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequestMapping(path = "/api/country")
public class CountryController {

    @Autowired
    private CountryRepository countryDAO;

    @GetMapping(path = "getEuropeanCountries", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Object> europeanCountries() {
        return countryDAO.getEuropeanCountries();
    }
}
