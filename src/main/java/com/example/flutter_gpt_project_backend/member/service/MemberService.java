package com.example.flutter_gpt_project_backend.member.service;


import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service("memberService")
public class MemberService {
//    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;

    public MemberService(/*JwtUtil jwtUtil,*/ MemberRepository memberRepository, PasswordEncoder encoder, ModelMapper modelMapper) {
     /*   this.jwtUtil = jwtUtil;*/
        this.memberRepository = memberRepository;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UUID signUp(Member member) {
        Optional<Member> validMember = memberRepository.findMemberByUserId(member.getUserId());

        if (validMember.isPresent()) {
            throw new IllegalArgumentException("This member UserId is already exist: " + member.getUserId());
        }
        member.setUserPw(encoder.encode(member.getUserPw())); // 비밀번호 암호화
        memberRepository.save(member);

        return member.getId();
    }
}
