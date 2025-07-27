package com.example.flutter_gpt_project_backend.member.service;

import org.springframework.stereotype.Service;

import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;


@Service
public class CustomUserDetailsService  {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


}
