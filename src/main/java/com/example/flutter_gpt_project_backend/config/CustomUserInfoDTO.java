package com.example.flutter_gpt_project_backend.config;

import com.example.flutter_gpt_project_backend.member.dto.request.MemberReqDTO;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CustomUserInfoDTO extends MemberReqDTO implements UserDetails {

    private UUID id;
    private String userId;
    private String userPw;
    private String email;
    private String name;
    private Role role;

CustomUserInfoDTO() {}
    public CustomUserInfoDTO(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.userPw = member.getUserPw();
        this.email = member.getEmail();
        this.name = member.getName();
        this.role = member.getRole();
    }

    public static UserDetails toSpringUser(Member member) {
        return new CustomUserInfoDTO(member);
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
