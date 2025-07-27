package com.example.flutter_gpt_project_backend.member.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GoogleMember extends Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String loginId;
    @Column(name = "email", length = 50, updatable = false, unique = true)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "user_pw", nullable = false)
    private String userPw;
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_ref_id")
    private GoogleProvider provider;




    // provider : google이 들어감

    public GoogleMember(){}



    public GoogleMember(UUID id, String email, String name, String userId, String userPw, String nickname, Role role, GoogleProvider provider, String loginId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.nickname = nickname;
        this.role = role;
        this.provider = provider;

        this.loginId = loginId;
    }


    public static class Builder {
        private String loginId;
        private UUID id;
        private String email;
        private String name;
        private String userId;
        private String userPw;
        private String nickname;
        private Role role;
        private GoogleProvider provider;

        public Builder() {}

        public Builder loginId(String loginId) { this.loginId = loginId; return this; }

        public Builder id(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder userId(String userId) { this.userId = userId; return this; }
        public Builder userPw(String userPw) { this.userPw = userPw; return this; }
        public Builder nickname(String nickname) { this.nickname = nickname; return this; }
        public Builder role(Role role) { this.role = role; return this; }
        public Builder provider(GoogleProvider provider) { this.provider = provider; return this; }

        public GoogleMember build() {
            return new GoogleMember(id, email, name, userId, userPw, nickname, role, provider, loginId);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    // Getters and Setters

    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public GoogleProvider getProvider() {
        return provider;
    }
    public void setProvider(GoogleProvider provider) {
        this.provider = provider;
    }





}
