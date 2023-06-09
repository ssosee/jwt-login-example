package seaung.jwtlogin.api.service.member.request;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberRegisterService {
    private String userId;
    private String password1;
    private String password2;

    @Builder
    private MemberRegisterService(String userId, String password1, String password2) {
        this.userId = userId;
        this.password1 = password1;
        this.password2 = password2;
    }
}
