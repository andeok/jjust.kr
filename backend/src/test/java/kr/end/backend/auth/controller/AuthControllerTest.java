package kr.end.backend.auth.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.end.backend.auth.dto.request.SignupRequest;
import kr.end.backend.member.domain.Member;
import kr.end.backend.member.dto.response.MemberResponse;
import kr.end.backend.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @MockitoBean
  private MemberService memberService;

  @MockitoBean
  private PasswordEncoder passwordEncoder;

  private SignupRequest signupRequest;
  private MemberResponse memberResponse;

  @BeforeEach
  void setUp() {
    // 테스트용 요청 객체 생성
    signupRequest = new SignupRequest(
        "test@example.com",
        "password123",
        "testuser"
        // 필요한 다른 필드들
    );

    Member member = Member.builder()
        .id(3L)
        .email("test@example.com")
        .nickname("testuser")
        .build();

    memberResponse = new MemberResponse(member);
  }

  @Test
  void signup() throws Exception {
    // Given
    given(memberService.signupMember(any(SignupRequest.class))).willReturn(memberResponse);

    // When
    ResultActions result = mockMvc.perform(
        post("/api/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signupRequest))
    );

    // Then - 응답에는 MemberResponse가 포함되어야 함
    result.andExpect(status().isOk()) // ResponseEntity.ok()는 200 상태를 반환
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.email").value("test@example.com"))
        .andExpect(jsonPath("$.nickname").value("testuser"))
        .andDo(document("member-signup",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestFields(
                fieldWithPath("email").description("회원 이메일").type(JsonFieldType.STRING),
                fieldWithPath("password").description("회원 비밀번호").type(JsonFieldType.STRING),
                fieldWithPath("nickname").description("회원 닉네임").type(JsonFieldType.STRING)
                // 필요한 다른 필드들도 추가
            ),
            responseFields(
                fieldWithPath("id").description("회원 ID").type(JsonFieldType.NUMBER),
                fieldWithPath("email").description("회원 이메일").type(JsonFieldType.STRING),
                fieldWithPath("nickname").description("회원 닉네임").type(JsonFieldType.STRING)
                // 응답에 포함된 다른 필드들도 추가
            )
        ));
  }
}