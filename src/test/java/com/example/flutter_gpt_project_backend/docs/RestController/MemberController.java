package com.example.flutter_gpt_project_backend.docs.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/remove-token")
    public ResponseEntity<?> removeToken(Authentication authentication) {
        // 실제 동작 코드
        return ResponseEntity.ok(Map.of("memberId", 123));
    }

}

