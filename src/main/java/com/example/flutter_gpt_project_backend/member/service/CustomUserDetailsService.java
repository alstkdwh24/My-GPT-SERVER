package com.example.flutter_gpt_project_backend.member.service;

import com.example.flutter_gpt_project_backend.config.CustomUserDetails;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member= memberRepository
                .findMemberByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(member);

    }
}
