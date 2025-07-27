package com.example.flutter_gpt_project_backend.member.restcontroller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flutter_gpt_project_backend.member.dto.request.LoginRequestDTO;
import com.example.flutter_gpt_project_backend.member.dto.request.MemberReqDTO;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    @Autowired
    @Qualifier("memberService")
    private final MemberService memberService;

    private final ModelMapper modelMapper;


    public JwtController(MemberService memberService, ModelMapper modelMapper) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UUID> signUp(@Valid @RequestBody MemberReqDTO dto) {
    Member member = modelMapper.map(dto, Member.class);
    UUID id= memberService.signUp(member);

        return ResponseEntity.status(HttpStatus.OK).body(id);
}
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO dto) {
        String loginMember = memberService.login(dto);

        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(loginMember);
    }

}
