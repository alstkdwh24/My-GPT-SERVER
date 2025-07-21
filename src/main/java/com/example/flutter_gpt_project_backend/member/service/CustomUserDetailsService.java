package com.example.flutter_gpt_project_backend.member.service;

import com.example.flutter_gpt_project_backend.config.CustomSecurityUserDetails;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService  {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


}
