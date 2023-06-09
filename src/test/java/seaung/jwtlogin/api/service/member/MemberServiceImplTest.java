package seaung.jwtlogin.api.service.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import seaung.jwtlogin.api.exception.MemberException;
import seaung.jwtlogin.api.service.member.request.MemberRegisterService;
import seaung.jwtlogin.api.service.member.response.MemberResponse;
import seaung.jwtlogin.domain.member.entity.Member;
import seaung.jwtlogin.domain.member.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원정보를 받으면 회원을 저장하고 회원가입이 된다.")
    void registerMember() {
        // given
        // 회원가입 요청 데이터 생성
        MemberRegisterService memberRegisterService = MemberRegisterService.builder()
                .userId("user1")
                .password1("123")
                .password2("123")
                .build();

        // when
        // 회원가입
        MemberResponse response = memberService.register(memberRegisterService);

        // then
        // 회원가입 리턴값으로 검증
        assertAll(
                () -> assertThat(response.getId()).isNotNull(),
                () -> assertThat(response.getUserId()).isEqualTo("user1")
        );
    }

    @Test
    @DisplayName("회원가입시 회원 아이디가 중복되면 예외가 발생한다.")
    void registerMemberDuplicateUserId() {
        // given
        String userId = "user1";

        // 회원아이디 중복 상황을 만들기 위해 DB에 저장한다.
        Member member = Member.create(userId, "123");
        memberRepository.save(member);

        // 회원가입 요청데이터 생성
        MemberRegisterService memberRegisterService = MemberRegisterService.builder()
                .userId(userId)
                .password1("123")
                .password2("123")
                .build();

        // when // then
        // 회원가입 시 검증
        assertThatThrownBy(() -> memberService.register(memberRegisterService))
                .isInstanceOf(MemberException.class)
                .hasMessage("이미 존재하는 아이디 입니다.");
    }

    @Test
    @DisplayName("회원가입시 비밀번호가 일치하지 않으면 예외가 발생한다.")
    void registerMemberNotEqualsPassword() {
        // given
        // 비밀번호가 다른 회원가입 요청 데이터 생성
        String userId = "user1";
        String password1 = "123";
        String password2 = "321";

        MemberRegisterService memberRegisterService = MemberRegisterService.builder()
                .userId(userId)
                .password1(password1)
                .password2(password2)
                .build();

        // when // then
        // 회원가입시 검증
        assertThatThrownBy(() -> memberService.register(memberRegisterService))
                .isInstanceOf(MemberException.class)
                .hasMessage("비밀번호가 일치하지 않습니다.");
    }
}