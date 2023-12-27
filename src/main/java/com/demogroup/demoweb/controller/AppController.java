package com.demogroup.demoweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AppController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
