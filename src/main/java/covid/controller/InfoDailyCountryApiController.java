package covid.controller;

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
    
    
    @GetMapping(path = "stats", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Object> getStatsCountry(@RequestParam(required = true) final String nameCountry) {
		return infoDailyDAO.getStatsCountry(nameCountry);
	}
}
