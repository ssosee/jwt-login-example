package seaung.jwtlogin.api.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import seaung.jwtlogin.api.controller.member.request.MemberRegisterRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper om;

    @Test
    @DisplayName("회원가입을 한다.")
    void registerMember() throws Exception{
        // given
        MemberRegisterRequest request = MemberRegisterRequest.builder()
                .userId("user1")
                .password1("123")
                .password2("123")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("회원가입 성공"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userId").value("user1"))
                ;
    }

    @Test
    @DisplayName("회원가입시 아이디가 없으면 예외가 발생한다.")
    void registerMemberValidationUserId() throws Exception{
        // given
        MemberRegisterRequest request = MemberRegisterRequest.builder()
                .password1("123")
                .password2("123")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("아이디는 필수 값 입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(100))
        ;
    }

    @Test
    @DisplayName("회원가입시 비밀번호가 없으면 예외가 발생한다.")
    void registerMemberValidationPassword() throws Exception{
        // given
        MemberRegisterRequest request = MemberRegisterRequest.builder()
                .userId("user1")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("패스워드는 필수 값 입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(100))
        ;
    }
}