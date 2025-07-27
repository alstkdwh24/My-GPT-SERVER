package com.example.flutter_gpt_project_backend.gpt.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "gpt_rooms")
public class GptRoomEntity {

    @Id
//    @UuidGenerator
    private UUID room_id;

    @Column(name = "room_name", nullable = false, unique = true)
    private String room_name;


    public GptRoomEntity() {
    }

    public GptRoomEntity(UUID room_id, String room_name) {
        this.room_id = room_id;
        this.room_name = room_name;
    }


    public UUID getRoom_id() {
        return room_id;
    }

    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;

    }



}
