package seaung.jwtlogin.api.service.member;

import seaung.jwtlogin.api.service.member.request.MemberRegisterService;
import seaung.jwtlogin.api.service.member.response.MemberResponse;

public interface MemberService {
    // 회원가입
    MemberResponse register(MemberRegisterService request);
    // 로그인
    // 로그아웃
}
