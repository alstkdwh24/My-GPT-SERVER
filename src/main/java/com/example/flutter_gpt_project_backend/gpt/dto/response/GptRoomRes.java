package com.example.flutter_gpt_project_backend.gpt.dto.response;

import java.util.UUID;

public class GptRoomRes {
    private UUID room_id;
    private String roomName;

    private String statusMessage;

    public GptRoomRes() {
    }

    public GptRoomRes( UUID room_id,String roomName, String statusMessage) {
        this.room_id = room_id;
        this.statusMessage =statusMessage ;
        this.roomName = roomName;



    }

    // Getters and Setters
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public UUID getRoom_id() {
        return room_id;
    }

    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }
}
