package com.example.flutter_gpt_project_backend.docs.RestController;

import com.example.flutter_gpt_project_backend.member.entity.Member;
import com.example.flutter_gpt_project_backend.member.repository.MemberRepository;
import com.example.flutter_gpt_project_backend.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberControllerTest {

    @Value("${jwtSecretKey}")
    private String jwtSecretKeys;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String username = "2";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    public void setupUser() {
        try {
            memberService.loadUserByUsername(username);
        } catch (Exception e) {
            // 유저가 없으면 생성
            Member member = new Member();
            member.setUserId(username);
            member.setUserPw(passwordEncoder.encode("testpassword"));
            member.setEmail("testuser@example.com");
            member.setNickname("TestUser");
            member.setName("TestUser");
            // 필요한 필드 세팅
            memberRepository.save(member);
        }
    }

    @Test
    public void removeToken_200() throws Exception {
        UserDetails userDetails = memberService.loadUserByUsername(username);

        mockMvc.perform(get("/member/remove-token")
                        .with(SecurityMockMvcRequestPostProcessors.user(userDetails)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
                        preprocessRequest(modifyHeaders().remove("Content-Length").remove("Host"),
                                prettyPrint()),
                        preprocessResponse(modifyHeaders().remove("Content-Length").remove("X-Content-Type-Options")
                                .remove("X-XSS-Protection").remove("Cache-Control").remove("Pragma")
                                .remove("Expires").remove("X-Frame-Options"), prettyPrint()),
                        responseFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("멤버 고유 번호")
                        )));
    }

}
