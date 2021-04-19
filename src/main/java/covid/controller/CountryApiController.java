package covid.controller;

import covid.dao.CountryRepository;
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

    @Autowired
    private CountryRepository countryDAO;

    @GetMapping(path = "continent", produces = MediaType.APPLICATION_JSON_VALUE)
        public @ResponseBody List<Object> getCountriesByContinent(@RequestParam(required = true) final String nameContinent) {
            return countryDAO.getCountriesByContinent(nameContinent);
        }
    
    @GetMapping(path = "getCountry", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Object getCountry(@RequestParam(required = true) final String nameCountry) {
		return countryDAO.getCountry(nameCountry);
	}
}