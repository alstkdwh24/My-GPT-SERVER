package com.example.flutter_gpt_project_backend.gpt.dto.request;

import java.util.UUID;

public class GptRoomReq {

    private UUID room_id;
    private String roomName;


    public GptRoomReq() {
    }

    public GptRoomReq(UUID room_id,String roomName) {
        this.room_id = room_id;
        this.roomName = roomName;

    }


    public UUID getRoom_id() {
        return room_id;
    }
    public UUID setRoo_id(UUID room_id) {
        this.room_id = room_id;
        return room_id;
    }

    // Getters and Setters
    public String getRoomName() {
        return roomName;
    }

    public String setRoomName(String roomName) {

        return roomName;
    }


}
