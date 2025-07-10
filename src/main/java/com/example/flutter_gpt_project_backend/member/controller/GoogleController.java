package com.example.flutter_gpt_project_backend.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth-login")
public class GoogleController {

        @GetMapping("/login/google_login")
    public String googleLogin() {

        return "oauth-login/login/google_login";  // 템플릿 경로
    }
    @GetMapping("/login")
    public String login() {
        return "oauth-login/login";  // 템플릿 경로
    }

    @GetMapping("/login/home")
    public String home() {
        return "oauth-login/login/home";  // 템플릿 경로
    }


}
