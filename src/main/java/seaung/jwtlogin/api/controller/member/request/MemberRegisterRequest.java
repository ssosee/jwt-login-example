package seaung.jwtlogin.api.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import seaung.jwtlogin.api.service.member.request.MemberRegisterService;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberRegisterRequest {
    @NotBlank(message = "아이디는 필수 값 입니다.")
    private String userId;
    @NotBlank(message = "패스워드는 필수 값 입니다.")
    private String password1;
    @NotBlank(message = "패스워드는 필수 값 입니다.")
    private String password2;

    @Builder
    private MemberRegisterRequest(String userId, String password1, String password2) {
        this.userId = userId;
        this.password1 = password1;
        this.password2 = password2;
    }

    public MemberRegisterService toService() {
        return MemberRegisterService.builder()
                .userId(userId)
                .password1(password1)
                .password2(password2)
                .build();
    }
}
