package covid.controller;

import covid.dao.CountryRepository;
import covid.dao.InfoDailyCountryRepository;
import covid.entity.InfoDailyCountry;
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

    @Autowired
    private CountryRepository countryDAO;
    
    @Autowired
    private InfoDailyCountryRepository infoDailyDAO;

    @GetMapping(path = "continent", produces = MediaType.APPLICATION_JSON_VALUE)
        public @ResponseBody List<Object> getNewCasesByContinent(@RequestParam(required = true) final String nameContinent) {
            LocalDate today = LocalDate.now().minusDays(2);
            return infoDailyDAO.getNewCases(nameContinent, today);
        }
    
    @GetMapping(path = "getCountry", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Object getCountryInfos(@RequestParam(required = true) final String nameCountry) {
		return countryDAO.getCountryByName(nameCountry);
	}
}