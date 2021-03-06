package com.mirage.controllers;

import com.mirage.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mirage on 26/02/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private SecurityService securityService;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping("/home")
    public String adminPage(Model model){
        model = setAdminGlobalModel(model);

        return "admin/main";
    }

    private Model setAdminGlobalModel(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", securityService.isUser(auth));
        model.addAttribute("isAdmin", securityService.isAdmin(auth));
        model.addAttribute("headerToDisplay", "solid");

        model.addAttribute("username", auth.getName());

        return model;
    }
}
