package covid.controller;

import covid.dao.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/show") 
public class ContinentController {

    @Autowired
    ContinentRepository continentDAO;

    @GetMapping(path = "map")
    public String showContinentList(Model model) {
        model.addAttribute("continents", continentDAO.findAll());
        return "maps";
    }

}
