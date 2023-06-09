package seaung.jwtlogin.api.service.member.response;

import lombok.Builder;
import lombok.Data;
import seaung.jwtlogin.domain.member.entity.Member;

@Data
public class MemberResponse {
    private Long id;
    private String userId;

    @Builder
    private MemberResponse(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .build();
    }
}
