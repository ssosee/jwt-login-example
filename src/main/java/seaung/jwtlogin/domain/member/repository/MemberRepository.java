package seaung.jwtlogin.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seaung.jwtlogin.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUserId(String userId);
}
