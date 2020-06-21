package com.wbz.toefl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String homePage() {
        return "index";
    }

    @RequestMapping("/main")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/footer")
    public String footerPage() {
        return "footer";
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }
}
