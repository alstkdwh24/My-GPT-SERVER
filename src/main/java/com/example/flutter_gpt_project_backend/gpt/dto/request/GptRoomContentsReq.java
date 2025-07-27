package com.example.flutter_gpt_project_backend.gpt.dto.request;


import java.time.LocalDateTime;
import java.util.UUID;

public class GptRoomContentsReq {

    private UUID id;
    private UUID room_id; // 타입을 String으로 변경

    private String content;

    private LocalDateTime registration_time;


    public GptRoomContentsReq() {
    }


    public GptRoomContentsReq(UUID id, UUID room_id, String content) {
        this.id = id;
        this.room_id = room_id;
        this.content = content;
        this.registration_time = LocalDateTime.now();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;

    }



    public UUID getRoom_id() {
        return room_id;
    }

    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(LocalDateTime registration_time) {
        this.registration_time = registration_time;
    }

}
