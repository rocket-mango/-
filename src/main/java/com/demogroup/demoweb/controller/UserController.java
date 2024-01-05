package com.demogroup.demoweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/login")
    public String loginPage(@RequestParam String username, @RequestParam String password){

        System.out.println(username);
        System.out.println(password);
        return "login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @GetMapping("/modify")
    public String modifyPage(){
        return "modify";
    }

}
