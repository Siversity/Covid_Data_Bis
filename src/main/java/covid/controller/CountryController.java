package covid.controller;

import covid.dao.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/country")
public class CountryController {
    
    @Autowired
    private CountryRepository countryDao;
    
    @GetMapping(path="show")
    public String afficheTousCountries(Model model){
        model.addAttribute("countries", countryDao.findAll());
        return "europe";
    }
}


