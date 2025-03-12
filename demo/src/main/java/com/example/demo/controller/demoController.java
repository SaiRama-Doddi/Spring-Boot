package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoController {

 @GetMapping("/")
    public String getHome(){
        return "home";
    }


    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }

    @GetMapping("/systems")
    public String showSystems(){
        return "systems";
    }


    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
    
    


}
