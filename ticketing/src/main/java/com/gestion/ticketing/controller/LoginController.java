package com.gestion.ticketing.controller;

import com.gestion.ticketing.model.User;
import com.gestion.ticketing.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    UserService userService;

    @GetMapping("/formulaire")
    public ModelAndView getFormulaire(){
        return new ModelAndView("login");
    }

    @PostMapping("/verify")
    public Object verifyLogin(@RequestParam("login") String login, @RequestParam("mdp") String mdp, HttpSession session)throws Exception
    {
        try
        {
            ModelAndView mv=new ModelAndView("insertionAgeEnfant");
            User user = userService.verifyLogin(login, mdp);
            session.setAttribute("user",user);
           /* List<SimpleGrantedAuthority> authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if(user.isStatus())
            {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getMdp(),authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);*/

            return "redirect:/ageEnfant/form";
        }
        catch (Exception e)
        {
            ModelAndView mv2=new ModelAndView("error");
            e.printStackTrace();
            mv2.addObject("error",e.getMessage());
            return mv2;
        }

    }

}
