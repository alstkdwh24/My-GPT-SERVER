package com.example.flutter_gpt_project_backend.gpt.restcontroller;

import com.example.flutter_gpt_project_backend.gpt.dto.request.GroqGPTDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<String> groqAsk(@RequestBody GroqGPTDTO dto, HttpSession session) {
        List<GroqGPTDTO.Message> message = dto.getMessages();
        if (message == null) {
            return ResponseEntity.badRequest().body("messages 필드가 누락되었습니다.");
        }
        String url = "https://api.groq.com/openai/v1/chat/completions";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(groqcloudGPT); // Groq Cloud API Key 설정
        List<Map<String,String>> messages=new ArrayList<>();
        for(GroqGPTDTO.Message msg: message){
            if(msg.getRole() == null || msg.getContent() == null) {
                return ResponseEntity.badRequest().body("Invalid message format: role or content is missing.");
        }
            Map<String, String>  map = new HashMap<>();
            map.put("role", msg.getRole());
            map.put("content", msg.getContent());
            messages.add(map);
        }


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
        requestBody.put("messages", messages);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println("Response: " + response.getBody());
        session.setAttribute("lastGptResponse", response.getBody());
        System.out.println(1);
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
        List<GroqGPTDTO.Message> message = dto.getMessages();
        String uri = "https://api.together.xyz/v1/chat/completions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TogetherAIGPTs); // Together AI API Key 설정
        List<Map<String, String>> messages = new ArrayList<>();
        for (GroqGPTDTO.Message msg : message) {
            if (msg.getRole() == null || msg.getContent() == null) {
                return ResponseEntity.badRequest().body("Invalid message format: role or content is missing.");
            }
            Map<String, String> map = new HashMap<>();
            map.put("role", msg.getRole());
            map.put("content", msg.getContent());
            messages.add(map);
        }
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "meta-llama/Llama-3.3-70B-Instruct-Turbo-Free");
        requestBody.put("messages", messages);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        System.out.println("Response: " + response.getBody());
        session.setAttribute("lastGptResponse", response.getBody());
        System.out.println("11111");
        return ResponseEntity.ok(response.getBody());


    }

}
