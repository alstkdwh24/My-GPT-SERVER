package com.example.flutter_gpt_project_backend.member.restcontroller;

import com.example.flutter_gpt_project_backend.member.dto.request.MemberReqDTO;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.service.MemberService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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


}
