package seaung.jwtlogin.api.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seaung.jwtlogin.api.controller.member.request.MemberRegisterRequest;
import seaung.jwtlogin.api.response.ApiResponse;
import seaung.jwtlogin.api.service.member.MemberService;
import seaung.jwtlogin.api.service.member.response.MemberResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<MemberResponse>> register(@Valid @RequestBody MemberRegisterRequest request) {
        MemberResponse register = memberService.register(request.toService());

        return new ResponseEntity<>(ApiResponse.of("회원가입 성공", HttpStatus.OK, register), HttpStatus.OK);
    }

}
