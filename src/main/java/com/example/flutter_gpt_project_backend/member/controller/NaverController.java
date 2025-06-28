package com.example.flutter_gpt_project_backend.member.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/naver")
public class NaverController {
    @GetMapping("/naverLoginComplete")
    public String naverLoginComplete(   @RequestParam("code") String code,
                                        @RequestParam("state") String state, HttpSession session) {
        System.out.println("네이버 로그인 완료! code=" + code + ", state=" + state);
        session.setAttribute("code", code);
        session.setAttribute("state", state);

        System.out.println("네이버 로그인 완료 페이지로 이동합니다.");
        return "/api/naver/naverLoginComplete";
    }
}
