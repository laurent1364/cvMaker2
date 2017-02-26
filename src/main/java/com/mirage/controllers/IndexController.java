package com.mirage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mirage on 24/02/2017.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", ""})
    public String index(){
        return "index";
    }

    @RequestMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
