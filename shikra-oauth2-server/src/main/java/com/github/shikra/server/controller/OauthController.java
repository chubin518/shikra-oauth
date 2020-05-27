package com.github.shikra.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth")
public class OauthController {
    @GetMapping("/sign_in")
    public String signIn() {
        return "signIn";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
