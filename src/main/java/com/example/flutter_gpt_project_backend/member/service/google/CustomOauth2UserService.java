package com.example.flutter_gpt_project_backend.member.service.google;

import com.example.flutter_gpt_project_backend.config.CustomOauth2UserDetails;
import com.example.flutter_gpt_project_backend.config.GoogleUserDetails;
import com.example.flutter_gpt_project_backend.config.OAuth2UserInfo;
import com.example.flutter_gpt_project_backend.member.entity.GoogleMember;
import com.example.flutter_gpt_project_backend.member.entity.GoogleProvider;
import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.entity.Role;
import com.example.flutter_gpt_project_backend.member.repository.GoogleProviderRepository;
import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customOauth2UserService")
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    private final GoogleProviderRepository googleProviderRepository;

    public CustomOauth2UserService(MemberRepository memberRepository, GoogleProviderRepository googleProviderRepository) {
        this.memberRepository = memberRepository;
        this.googleProviderRepository = googleProviderRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("OAuth2 User Info: {}", oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = null;
        if (provider.equals("google")) {
            log.info("Google Oauth2 User Info");
            oAuth2UserInfo = new GoogleUserDetails(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String loginId = provider + "_" + providerId;
        String name = oAuth2UserInfo.getName();

        Optional<GoogleMember> findMember = memberRepository.findByLoginId(loginId);
        GoogleProvider providerEntity = new GoogleProvider(provider, providerId, loginId);
        GoogleMember member;
        googleProviderRepository.save(providerEntity);

        if (findMember.isEmpty()) {
            member = GoogleMember.builder()
                    .email(email)
                    .name(name)
                    .provider(providerEntity) // Provider 객체 직접 전달
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        } else {
            member = findMember.get();
        }

        return new CustomOauth2UserDetails(member, oAuth2User.getAttributes());
    }
}
