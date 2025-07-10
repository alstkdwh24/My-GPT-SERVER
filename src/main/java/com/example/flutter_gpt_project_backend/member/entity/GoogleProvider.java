package com.example.flutter_gpt_project_backend.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "GoogleProvider")
public class GoogleProvider {

    public GoogleProvider() {
    }

    public GoogleProvider(String provider, String providerId, String loginId) {
        this.provider = provider;
        this.providerId = providerId;
        this.loginId = loginId;
    }

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "provider", nullable = false)
    private String provider;

    // providerId : 구굴 로그인 한 유저의 고유 ID가 들어감
    @Column(name = "providerId", nullable = false)
    private String providerId;
    @Column(name = "loginId", unique = true, nullable = false)
    private String loginId;


    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
//        public static Provider getProvider(String providerId) {
//            // providerId를 이용하여 Provider 객체를 조회하는 로직을 구현해야 합니다.
//            // 예시로는 데이터베이스에서 조회하거나, 하드코딩된 값을 반환할 수 있습니다.
//            return new Provider(providerId, "google", "googleLoginId");
//
//



}

