package com.gestion.ticketing.controller;

import com.gestion.ticketing.model.AgeEnfantMaxRemise;
import com.gestion.ticketing.model.User;
import com.gestion.ticketing.service.EnfantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ageEnfant")
public class GestionAgeController {


    @Autowired
    EnfantService enfantService;

    @PostMapping("/insert")
    public ModelAndView insert(HttpSession session, @RequestParam("ageMax") int ageMax, @RequestParam("dateEns") String dateEns, @RequestParam("remise") double remise ) throws Exception
    {
        if(session.getAttribute("user")!=null && ((User)(session.getAttribute("user"))).isStatus()) {
            java.sql.Date date = java.sql.Date.valueOf(dateEns);
            AgeEnfantMaxRemise ageEnfantMaxRemise = new AgeEnfantMaxRemise();
            ageEnfantMaxRemise.setAgeMax(ageMax);
            ageEnfantMaxRemise.setRemise(remise);
            ageEnfantMaxRemise.setDateEns(date);
            enfantService.insertAgeRemise(ageEnfantMaxRemise);
        }
        return form();
    }
    @GetMapping("/form")
    public ModelAndView form()
    {
        return  new ModelAndView("insertionAgeEnfant");
    }
}
