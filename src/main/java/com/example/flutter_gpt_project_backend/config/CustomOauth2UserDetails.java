package com.example.flutter_gpt_project_backend.config;

import com.example.flutter_gpt_project_backend.member.entity.GoogleMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOauth2UserDetails implements UserDetails, OAuth2User {

    private GoogleMember googleMember;
    private Map<String, Object> attributes;

    public void CustomUserDetails(GoogleMember googleMember, Map<String, Object> attributes) {
        this.googleMember = googleMember;
        this.attributes = attributes;
    }

    public CustomOauth2UserDetails(GoogleMember googleMember) {
        this.googleMember = googleMember;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority(){
            @Override
            public String getAuthority(){
                return googleMember.getRole().name();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return googleMember.getUserPw();
    }

    @Override
    public String getUsername() {
        return googleMember.getUserId();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;

    }

}
