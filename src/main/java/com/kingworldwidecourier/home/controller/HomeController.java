package com.kingworldwidecourier.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/welcome")
    public String welcome(){
        String text="vfsdgsergs dsfgdf";
        return text;
    }

}
