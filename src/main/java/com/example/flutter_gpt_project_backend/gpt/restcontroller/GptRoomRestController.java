package com.example.flutter_gpt_project_backend.gpt.restcontroller;

import com.example.flutter_gpt_project_backend.gpt.dto.request.GptRoomContentsReq;
import com.example.flutter_gpt_project_backend.gpt.dto.request.GptRoomReq;
import com.example.flutter_gpt_project_backend.gpt.dto.response.GptRoomRes;
import com.example.flutter_gpt_project_backend.gpt.entity.GptRoomEntity;
import com.example.flutter_gpt_project_backend.gpt.service.GptRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/gpt/rooms")
public class GptRoomRestController {

    @Autowired
    private GptRoomService gptRoomService;

    @Autowired
    public GptRoomRestController(GptRoomService gptRoomService) {
        this.gptRoomService = gptRoomService;
    }

    @PostMapping("/createGptRoom")
    ResponseEntity<GptRoomRes> createRoom() {
        String roomName= "room" + UUID.randomUUID();// 채팅방 이름 생성

        GptRoomRes createdRoom = gptRoomService.createGptRoom(roomName);


        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);


    }

    @PostMapping("/createGptContents")
    ResponseEntity<GptRoomRes> createGptContents(@RequestBody GptRoomContentsReq dto) {
        UUID roomId = dto.getRoom_id();
        String content = dto.getContent();
        if (roomId != null) {
            System.out.println("roomId type: " + roomId.getClass().getName());
        } else {
            System.out.println("roomId is null");
        }

        gptRoomService.createGptContents(roomId,content);
        return null;
    }
}
