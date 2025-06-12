package com.example.flutter_gpt_project_backend.member.restcontroller;

import com.example.flutter_gpt_project_backend.member.dto.request.KakaoLoginDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 개발 중에는 *로 허용, 운영 시에는 도메인 제한

public class GPTRestController {
    @GetMapping("/kakaoUrl")
    public ResponseEntity<String> kakaoUrl() {
        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize";
        System.out.println("kakaoUrl: " + kakaoUrl);
        return ResponseEntity.ok(kakaoUrl);
    }

    @PostMapping("/kakaoToken")
    public ResponseEntity<String> kakaoToken(@RequestBody Map<String, String> body){
        String kakaoToken = body.get("kakaoToken");
        System.out.println("Access Token: " + kakaoToken);
        return ResponseEntity.ok("kakaoToken");
    }

}
