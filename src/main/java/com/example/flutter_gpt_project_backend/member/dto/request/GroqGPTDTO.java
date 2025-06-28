package com.example.flutter_gpt_project_backend.member.dto.request;

public class GroqGPTDTO {
    private String message;

    public GroqGPTDTO() {}
    public GroqGPTDTO(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
