/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.apicontroller;

import covid.dao.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequestMapping(path = "/api/continent")
public class ContinentApiController {

    @Autowired
    ContinentRepository continentDAO;

    // -- Nouvel ajout
    @GetMapping(path = "getContinent", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Object getInfosContinent(@RequestParam(required = true) final String nameContinent) {
        return continentDAO.getInfosContinentByName(nameContinent);

    }

}
