package com.example.flutter_gpt_project_backend.member.dto.request;

import com.example.flutter_gpt_project_backend.member.entity.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class MemberReqDTO {
    private UUID id;
    @NotBlank(message = "아이디는 필수 입력값입니다.")

    private String userId;
    private String userPw;

    private String email;


    private String name;


//    private String role;

    private String nickname;


    public MemberReqDTO() {}

    public MemberReqDTO(UUID id, String userId, String userPw, String email, String name, String role, String nickname) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.name = name;
//        this.role = role;
        this.nickname = nickname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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


//    public String getRole() {
//        return role;
//    }
//
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }




}
