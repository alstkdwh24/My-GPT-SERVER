package com.example.flutter_gpt_project_backend.member.restcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/google")
public class GoogleRestController {

    @Value("${google_OAuth2_0_client_ids}")
    private String googleClientId;

    @Value("${google_OAuth_client_pws}")
    private String googleClientSecret;

    @PostMapping("/getGoogleAccessToken")
    ResponseEntity<String> getGoogleAccessToken(@RequestParam String code, @RequestParam String state) {
        String url = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        parameter.add("grant_type", "authorization_code");
        parameter.add("client_id", googleClientId);
        parameter.add("client_secret", googleClientSecret);
        parameter.add("code", code);
        parameter.add("redirect_uri", "https://jo-my-gpt.com/oauth-login/login/home");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameter, headers);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println("Google Access Token Response: " + response.getBody());

        return response;
    }


}
