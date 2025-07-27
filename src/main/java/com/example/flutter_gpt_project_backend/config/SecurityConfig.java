package com.example.flutter_gpt_project_backend.config;

import com.example.flutter_gpt_project_backend.member.service.CustomUserDetailsService;
import com.example.flutter_gpt_project_backend.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final MemberService  memberService;
    private final JwtUtil jwtUtil;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private static final String[] AUTH_WHITELIST = {
      "/api/**", "/api/askGPT/**",
    };

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, MemberService memberService, JwtUtil jwtUtil, CustomAccessDeniedHandler customAccessDeniedHandler, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors((Customizer.withDefaults()));

        http.headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));
        //세션 관리 상태 없음으로 구성, Spring Security가 세션을 생성하지 않도록 설정
        http.sessionManagement(sessionMangement -> sessionMangement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
        ));

        //FOrmLogin, BasicHttp 비활성화
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new JwtAuthFilter(memberService, jwtUtil), UsernamePasswordAuthenticationFilter
                .class);

        // OAuth 2.0 로그인 방식 설정
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/oauth2/**", "/login/**", "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/api/oauth-login/login/home")  // 로그인 성공 후 이동 경로
                        .failureUrl("/oauth-login/login")                  // 로그인 실패 시 이동 경로
                );


        http
                .logout((auth) -> auth
                        .logoutUrl("/oauth-login/logout"));


        return http.build();
    }

}
