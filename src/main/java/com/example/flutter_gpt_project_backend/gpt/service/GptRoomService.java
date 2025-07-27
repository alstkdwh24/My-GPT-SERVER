package com.example.flutter_gpt_project_backend.gpt.service;

import com.example.flutter_gpt_project_backend.gpt.dto.request.GptRoomContentsReq;
import com.example.flutter_gpt_project_backend.gpt.dto.request.GptRoomReq;
import com.example.flutter_gpt_project_backend.gpt.dto.response.GptRoomRes;
import com.example.flutter_gpt_project_backend.gpt.entity.GptRoomContentEntity;
import com.example.flutter_gpt_project_backend.gpt.entity.GptRoomEntity;
import com.example.flutter_gpt_project_backend.gpt.repository.GptRoomContentRepository;
import com.example.flutter_gpt_project_backend.gpt.repository.GptRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("gptRoomService")
public class GptRoomService {

    // 여기에 GptRoomService의 메소드들을 추가하세요.
    // 예: 채팅방 생성, 채팅 메시지 전송, 채팅 기록 조회 등



    private final GptRoomRepository gptRoomRepository; // GptRoomRepository는 데이터베이스와의 상호작용을 담당합니다.

    public GptRoomService(GptRoomRepository gptRoomRepository, GptRoomContentRepository gptRoomContentRepository) {
        this.gptRoomRepository = gptRoomRepository;
        this.gptRoomContentRepository = gptRoomContentRepository;
    }

    private final GptRoomContentRepository gptRoomContentRepository;

    // 예시 메소드
    public GptRoomRes createGptRoom(String roomName) {
        UUID room_id = UUID.randomUUID();
        GptRoomEntity gptRoomReq = new GptRoomEntity();
        gptRoomReq.setRoom_name(roomName);
        gptRoomReq.setRoom_id(room_id);
       // 채팅방 생성 로직
        gptRoomRepository.save(gptRoomReq); // GptRoomRepository를 통해 채팅방 생성

        System.out.println("채팅방 " + roomName + "이(가) 생성되었습니다.");
        return new GptRoomRes(gptRoomReq.getRoom_id(), roomName,"성공했습니다."); // GptRoomRes 객체 반환

    }

    public void createGptContents(UUID room_id, String content) {
        GptRoomContentEntity gptRoomContentsReq = new GptRoomContentEntity ();
        // 채팅 메시지 전송 로직
        gptRoomContentsReq.setContent(content);
        gptRoomContentsReq.setRegistration_time(LocalDateTime.now()); // 현재 시간 설정
        GptRoomEntity roomEntity = gptRoomRepository.findById(room_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 room_id입니다."));
        gptRoomContentsReq.setRoom_id(roomEntity);



        gptRoomContentRepository.save(gptRoomContentsReq);        // 메시지 전송 로직
    }
}
