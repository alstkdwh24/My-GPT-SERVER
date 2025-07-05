package com.example.flutter_gpt_project_backend.config;

import com.example.flutter_gpt_project_backend.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomSecurityUserDetails implements UserDetails {
    private final Member member;

    public CustomSecurityUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + member
                .getRole().toString());

        return roles.stream()
                .map(SimpleGrantedAuthority :: new)
                .toList();
    }
    @Override
    public String getPassword() {
        return member.getUserPw();
    }

    @Override
    public String getUsername(){
        return member.getUserId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
