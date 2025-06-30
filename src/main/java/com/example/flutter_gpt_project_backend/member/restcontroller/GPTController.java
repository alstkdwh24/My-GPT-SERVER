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

    @Value("${GPT}")
    private String groqcloudGPT;

    @Value("${TogetherAIGPTs}")
    private String TogetherAIGPTs;

    @PostMapping("/groqAsk")
    public ResponseEntity<String> groqAsk( @RequestBody GroqGPTDTO dto, HttpSession session) {

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

        // 세션에 응답 저장
        session.setAttribute("lastGptResponse", response.getBody());
        return ResponseEntity.ok(response.getBody());

    }


//    @GetMapping("/groqResponse")
//    public ResponseEntity<String> groqResponse(HttpSession session) {
//        String lastResponse = (String) session.getAttribute("lastGptResponse");
//        if (lastResponse != null) {
//            return ResponseEntity.ok(lastResponse);
//        } else {
//            return ResponseEntity.status(404).body("No response found in session.");
//        }
//
//    }



    @PostMapping("/GPTAsk")
    public ResponseEntity<String> gptAsk(@RequestBody GroqGPTDTO dto, HttpSession session) {
        String messageAsk = dto.getMessage();
        System.out.println("Received messageAsk: " + messageAsk);

        String url = "https://api.together.xyz/v1/chat/completions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + TogetherAIGPTs);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "meta-llama/Llama-3.3-70B-Instruct-Turbo-Free");
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", messageAsk);
        messages.add(message);

        body.put("messages", messages);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("Response: " + response.getBody());

        // 세션에 응답 저장
        session.setAttribute("together", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

}
