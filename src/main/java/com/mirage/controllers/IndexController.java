package com.mirage.controllers;

import com.mirage.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mirage on 24/02/2017.
 */
@Controller
public class IndexController {

    private SecurityService securityService;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping({"/", ""})
    public String index(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", securityService.isUser(auth));
        model.addAttribute("isAdmin", securityService.isAdmin(auth));
        model.addAttribute("headerToDisplay", "transparent");

        if(auth.isAuthenticated()){
            model.addAttribute("username", auth.getName());
        }

        return "index";
    }

    @RequestMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

    @RequestMapping("/login")
    public String login(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(securityService.isUser(auth)){
            return "redirect:/about_me";
        }
        model.addAttribute("isUser", false);
        model.addAttribute("isAdmin", false);
        model.addAttribute("headerToDisplay", "transparent");

        return "login";
    }
}
