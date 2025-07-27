package com.example.flutter_gpt_project_backend.config;

import java.util.Map;

import lombok.Builder;

@Builder
public class GoogleUserDetails implements OAuth2UserInfo{

    private Map<String, Object> userAttributes;

    public GoogleUserDetails() {
    }
    public GoogleUserDetails(Map<String, Object> userAttributes) {
        this.userAttributes = userAttributes;
    }
    public Map<String, Object> getUserAttributes() {
        return userAttributes;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String) userAttributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String) userAttributes.get("email");
    }

    @Override
    public String getName() {
        return (String) userAttributes.get("name");
    }
}
