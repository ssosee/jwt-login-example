package seaung.jwtlogin.domain.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import seaung.jwtlogin.domain.member.entity.Member;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 아이디로 회원을 조회한다.")
    void findMemberByUserId() {

        // given
        Member member = Member.create("user1", "123");
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findMemberByUserId("user1").get();

        // then
        assertAll(
                () -> assertThat(findMember).isNotNull(),
                () -> assertThat(findMember.getUserId()).isEqualTo("user1")
        );
    }
}