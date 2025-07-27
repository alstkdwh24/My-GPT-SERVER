package com.example.flutter_gpt_project_backend.member.restcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.flutter_gpt_project_backend.member.dto.request.NaverTokenDTO;
import com.example.flutter_gpt_project_backend.member.dto.response.NaverUserInfoResponseDTO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/naver")
@CrossOrigin("*") // 모든 도메인에서 접근 허용
public class NaverRestController {
    @Value("${naverSecretIds}")
    private String naverSecretId;


    @Value("${naverSecretValues}")
    private String naverSecretValue;
    final String access_token = "";

    @PostMapping("/naverCode")
    public String naverLoginComplete(HttpSession session) {
        String code = (String) session.getAttribute("code");
        String state = (String) session.getAttribute("state");

        if (code == null || code.isEmpty()) {
            return "네이버 로그인 코드가 없습니다.";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        RestTemplate restTemplate = new RestTemplate();
        String tokenUrl = "https://nid.naver.com/oauth2.0/token?" +
                "grant_type=authorization_code" +
                "&client_id=" + naverSecretId +
                "&client_secret=" + naverSecretValue +
                "&code=" + code
                +
                "&state" + state;
        try {
            String response = restTemplate.postForObject(tokenUrl, headers, String.class);
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(response);
            String accessToken = jsonNode.get("access_token").asText();
            session.setAttribute("access_token", accessToken);
            System.out.println("Naver Login Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
            return "네이버 로그인 요청 중 오류가 발생했습니다.";
        }
        // 여기서 code를 사용해 토큰 요청 등 추가 작업 가능

        return "네이버 로그인 완료";
    }

    @GetMapping("/naverUserInfo")
    public ResponseEntity<NaverUserInfoResponseDTO> getNaverUserInfo(NaverTokenDTO dto, HttpSession session) {
        String access_token = session.getAttribute("access_token").toString();

        System.out.println("Access Token: " + access_token);

        String userInfoUrl = "https://openapi.naver.com/v1/nid/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + access_token);


        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<NaverUserInfoResponseDTO> response = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.GET,
                entity,
                NaverUserInfoResponseDTO.class
        );
        System.out.println("User Info: " + response.getBody().getResponse().getName());
        System.out.println("User Email: " + response.getBody().getResponse().getEmail());
        System.out.println("User ID: " + response.getBody().getResponse().getId());
        System.out.println("User Profile Image: " + response.getBody().getResponse().getProfileImage());
        System.out.println("User nickname: " + response.getBody().getResponse().getNickname());
        System.out.println("User response" + response.getBody().getResponse().toString());
// 또는
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        } else {

            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

}
