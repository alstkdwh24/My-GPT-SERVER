package com.example.flutter_gpt_project_backend.config;

public interface OAuth2UserInfo {
    String getProvider();
            String getProviderId();
    String getEmail();
    String getName();
}
