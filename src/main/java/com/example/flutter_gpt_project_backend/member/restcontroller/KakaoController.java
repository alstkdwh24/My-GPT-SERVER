package com.example.flutter_gpt_project_backend.member.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.flutter_gpt_project_backend.member.dto.request.KakaoLoginDTO;
import com.example.flutter_gpt_project_backend.member.dto.response.KakaoLoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/kakao")
@CrossOrigin(origins = "*") // 개발 중에는 *로 허용, 운영 시에는 도메인 제한
public class KakaoController {

    private String kakaoToken;


//    @GetMapping("/kakaoUrl")
//    public ResponseEntity<String> kakaoUrl() {
//        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize";
//        System.out.println("kakaoUrl: " + kakaoUrl);
//        return ResponseEntity.ok(kakaoUrl);
//    }

    @PostMapping("/kakaoToken")
    public ResponseEntity<String> kakaoToken(@RequestBody Map<String, String> body){
         kakaoToken = body.get("kakaoToken");
        System.out.println("Access Token: " + kakaoToken);
        return ResponseEntity.ok(kakaoToken);
    }

    @PostMapping("/userInfo")
    public ResponseEntity<KakaoLoginResponseDTO> kakaoUserInfo(@ModelAttribute KakaoLoginDTO dto) throws JsonProcessingException {
        String kakaoTokens = kakaoToken;
        System.out.println("Access Token: " + kakaoTokens);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + kakaoTokens);
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> body = new HashMap<>();
        body.put("property_keys", List.of("kakao_account.email", "kakao_account.profile"));
        String bodyJson = objectMapper.writeValueAsString(body);




      HttpEntity<String> requestEntity = new HttpEntity<>(bodyJson, headers);
        String url = "https://kapi.kakao.com/v2/user/me";
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);


        String nickname = null;
        String email = null;
        String profileImage=null;
        if(response.getBody() !=null){
            var root = objectMapper.readTree(response.getBody());
            var kakaoAccount = root.path("kakao_account");
            var profile = kakaoAccount.path("profile");
            nickname = profile.path("nickname").asText();
            email = kakaoAccount.path("email").asText();
            profileImage = profile.path("profile_image_url").asText();
        }
        KakaoLoginResponseDTO result = new KakaoLoginResponseDTO(nickname, email, profileImage);

        return ResponseEntity.ok(result);
    }
}
