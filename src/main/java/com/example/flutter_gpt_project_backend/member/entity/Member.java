package com.example.flutter_gpt_project_backend.member.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "user_id",  unique = true)
    private String userId;
    @Column(name = "user_pw")
    private String userPw;
    @Column(name = "email", length = 50, updatable = false, unique = true)

    private String email;
    @Column(name = "name", nullable = false)

    private String name;
    @Column(name = "nickname", nullable = false)

    private String nickname;

    @Enumerated(EnumType.STRING) // Enum 타입으로 변경
    private Role role;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Member() {}
    public Member(UUID id, String userId, String userPw, String email, String name, String nickname, Role role) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
    }
}
