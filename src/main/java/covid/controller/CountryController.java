package covid.controller;

import covid.dao.ContinentRepository;
import covid.dao.CountryRepository;
import covid.entity.Continent;
import covid.entity.Country;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/country")
public class CountryController {
    
    @Autowired
    private CountryRepository countryDAO;
    @Autowired
    private ContinentRepository continentDAO;
    
    @GetMapping(path="show")
    public String afficheTousCountries(Model model){
        
        // On créé une liste vide qui va acceuillir la liste des pays situés en Europe
        List countriesEurope = new LinkedList<Country>();
        // On sélectionne l'Europe
        for (Continent continent : continentDAO.findAll()) {    
        if (continent.getNameContinent() == "Europe") {
            // On ajoute tous les pays du continent dans notre liste
            for (Country countryEurope : continent.getCountries()) {
                countriesEurope.add(countryEurope);
            }
        }
    }
        // Renvoi des données
        model.addAttribute("countriesEurope", countryDAO.findAll());
        return "europe";
    }
}


