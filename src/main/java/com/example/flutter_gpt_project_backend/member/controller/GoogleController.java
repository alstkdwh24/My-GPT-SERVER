package com.example.flutter_gpt_project_backend.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/oauth-login/login")
public class GoogleController {

    @GetMapping("/home")
    public String googleLogin() {
        return "api/oauth-login/login/home";  // 템플릿 경로
    }
}
