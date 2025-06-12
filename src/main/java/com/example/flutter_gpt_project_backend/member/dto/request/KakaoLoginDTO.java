package com.example.flutter_gpt_project_backend.member.dto.request;



import java.util.UUID;


public class KakaoLoginDTO {

    private UUID uuid;

    private String code;
    private String accessToken;
    private String refreshToken;
    private String expiresIn;

    private String tokenType;

    private String clientId;

    private String redirectUri;

    public KakaoLoginDTO() {}

    public KakaoLoginDTO(UUID uuid, String accessToken, String refreshToken, String expiresIn, String code) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.uuid = uuid;
        this.code = code;
    }

    public UUID getUuid() {return uuid;}
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAccessToken() {return accessToken;}
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {return refreshToken;}
    public void setRefreshToken(String refreshToken) {this.refreshToken= refreshToken;}

    public String getExpiresIn() {return expiresIn;}
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;}

    public String getTokenType() {return tokenType;}
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getCode() {return code;}
    public void setCode(String code) {
        this.code = code;
    }
    public String getClientId() {return clientId;}
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {return redirectUri;}
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
