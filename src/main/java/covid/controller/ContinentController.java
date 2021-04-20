/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.controller;

import covid.dao.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/continents") 
public class ContinentController {

    @Autowired
    ContinentRepository continentDAO;

    @GetMapping(path = "map")
    public String showContinentList(Model model) {
        model.addAttribute("continents", continentDAO.findAll());
        return "maps";
    }

}
