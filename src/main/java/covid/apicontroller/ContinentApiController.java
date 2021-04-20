/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid.apicontroller;

import covid.dao.ContinentRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import covid.dto.InfoContinent;

@Service
@RequestMapping(path = "/api/continent")
public class ContinentApiController {

    @Autowired
    ContinentRepository continentDAO;

    // -- Nouvel ajout
    @GetMapping(path = "getContinent", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InfoContinent getInfosContinent(@RequestParam(required = true) final String nameContinent) {
        LocalDate today = LocalDate.now().minusDays(2);
        return continentDAO.getInfosContinentByName(nameContinent, today);

    }

}
