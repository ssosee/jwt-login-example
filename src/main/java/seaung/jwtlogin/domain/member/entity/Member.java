package seaung.jwtlogin.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seaung.jwtlogin.api.service.member.request.MemberRegisterService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;

    @Builder
    private Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static Member create(String userId, String password) {
        return Member.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
