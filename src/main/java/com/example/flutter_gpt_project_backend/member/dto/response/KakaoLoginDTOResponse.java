package com.example.flutter_gpt_project_backend.member.dto.response;

public class KakaoLoginDTOResponse {

    private String 	profile_nickname;
    private String  profile_image;
    private String account_email;

    public KakaoLoginDTOResponse(){}
    public KakaoLoginDTOResponse(String profile_nickname, String profile_image, String account_email) {
        this.profile_nickname = profile_nickname;
        this.profile_image = profile_image;
        this.account_email = account_email;
    }
    public String getProfile_nickname() {
        return profile_nickname;
    }
    public void setProfile_nickname(String profile_nickname) {
        this.profile_nickname = profile_nickname;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }



}
