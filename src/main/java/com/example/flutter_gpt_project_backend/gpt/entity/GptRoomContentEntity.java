package com.example.flutter_gpt_project_backend.gpt.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "gpt_room_chating")
public class GptRoomContentEntity {

    @Id
    @UuidGenerator
    @Column(name = "contents_id", nullable = false, unique = true)
    private UUID contents_id;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    private GptRoomEntity room_id;
    @Column(name = "contents", nullable = false, unique = true)

    private String content;
    @Column(name = "registration_time", nullable = false) // unique 제거

    private LocalDateTime registration_time;

    public GptRoomContentEntity() {
    }
    public GptRoomContentEntity(UUID contents_id, GptRoomEntity room_id, String content, LocalDateTime registration_time) {
        this.contents_id = contents_id;
        this.room_id = room_id;
        this.content = content;
        this.registration_time = registration_time;
    }

    public UUID getContents_id() {
        return contents_id;
    }

    public void setContents_id(UUID contents_id) {
        this.contents_id = contents_id;
    }

    public GptRoomEntity getRoom_id() {
        return room_id;
    }


    public void setRoom_id(GptRoomEntity room_id) {
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
