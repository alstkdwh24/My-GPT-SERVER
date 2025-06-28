package com.example.flutter_gpt_project_backend.member.restcontroller;

import com.example.flutter_gpt_project_backend.member.dto.request.GroqGPTDTO;
import com.example.flutter_gpt_project_backend.member.dto.request.KakaoLoginDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/askGPT")
@CrossOrigin(origins = "*") // 개발 중에는 *로 허용, 운영 시에는 도메인 제한

public class GPTController {

    @Value("${groqcloudGPT}")
    private String groqcloudGPT;

    @PostMapping("/groqAsk")
    public ResponseEntity<String> groqAsk( @RequestBody GroqGPTDTO dto) {

      String messageAsk= dto.getMessage();
        System.out.println("Received messageAsk: " + messageAsk);

        String url = "https://api.groq.com/openai/v1/chat/completions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + groqcloudGPT);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", messageAsk);
        messages.add(message);

        body.put("messages", messages);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("Response: " + response.getBody());
        return ResponseEntity.ok(response.getBody());

    }





}
