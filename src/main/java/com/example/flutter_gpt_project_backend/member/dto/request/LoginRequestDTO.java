package com.example.flutter_gpt_project_backend.member.dto.request;

import jakarta.validation.constraints.NotNull;

public class LoginRequestDTO {

    @NotNull(message = "아이디는 필수 입력값입니다.")
    private String user_id;
    @NotNull(message = "비밀번호는 필수 입력값입니다.")
    private String user_pw;
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }
    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }
    public LoginRequestDTO() {
    }
    public LoginRequestDTO(String user_id, String user_pw) {
        this.user_id = user_id;
        this.user_pw = user_pw;
    }
}
