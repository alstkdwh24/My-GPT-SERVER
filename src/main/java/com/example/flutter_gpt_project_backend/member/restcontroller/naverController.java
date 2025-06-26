package com.example.flutter_gpt_project_backend.member.restcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/api/naver")
@CrossOrigin("*") // 모든 도메인에서 접근 허용
public class naverController {
    @Value("${naverSecretId}")
    private String naverSecretId;


    @PostMapping("/naverLogin")
    public ResponseEntity<String> naverLogin() {
        String clientId = naverSecretId; // 네이버 개발자 센터에서 발급받은 클라이언트 ID
        String redirectUri = URLEncoder.encode("https://yourdomain.com/oauth/naver/callback", StandardCharsets.UTF_8);
        String state = UUID.randomUUID().toString(); // CSRF 방지용 state 값
        System.out.println("naverSecretId: " + naverSecretId);
        String naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize"
                + "?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&state=" + state;
        System.out.println("naverLoginUrl: " + naverLoginUrl + " "+ naverSecretId);
        System.out.println("naverLoginUrl: " + naverLoginUrl + " "+ naverSecretId);



        return ResponseEntity.ok(naverLoginUrl);    }

}
