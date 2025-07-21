package com.example.flutter_gpt_project_backend.member.service;


import com.example.flutter_gpt_project_backend.config.CustomUserInfoDTO;
import com.example.flutter_gpt_project_backend.config.JwtUtil;
import com.example.flutter_gpt_project_backend.member.dto.request.LoginRequestDTO;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service("memberService")
public class MemberService implements UserDetailsService {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;



    public MemberService(/*JwtUtil jwtUtil,*/ JwtUtil jwtUtil, MemberRepository memberRepository, PasswordEncoder encoder, ModelMapper modelMapper) {
        this.jwtUtil = jwtUtil;
        /*   this.jwtUtil = jwtUtil;*/
        this.memberRepository = memberRepository;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public String login(LoginRequestDTO dto){
        String user_id = dto.getUser_id();
        String user_pw = dto.getUser_pw();
        Optional<Member> member = memberRepository.findMemberByUserId(user_id);
        if(member.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        if(!encoder.matches(user_pw, member.get().getUserPw())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        CustomUserInfoDTO customUserInfoDTO = modelMapper.map(member.get(), CustomUserInfoDTO.class);
        return jwtUtil.createAccessToken(customUserInfoDTO);
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


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findMemberByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return CustomUserInfoDTO.toSpringUser(member);

    }
}
