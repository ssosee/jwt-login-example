package seaung.jwtlogin.api.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seaung.jwtlogin.api.exception.MemberException;
import seaung.jwtlogin.api.service.member.request.MemberRegisterService;
import seaung.jwtlogin.api.service.member.response.MemberResponse;
import seaung.jwtlogin.domain.member.entity.Member;
import seaung.jwtlogin.domain.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public MemberResponse register(MemberRegisterService request) {

        boolean isMember = memberRepository.findMemberByUserId(request.getUserId()).isPresent();
        if(isMember) throw new MemberException("이미 존재하는 아이디 입니다.");

        boolean passwordIsEq = request.getPassword1().equals(request.getPassword2());
        if(!passwordIsEq) throw new MemberException("비밀번호가 일치하지 않습니다.");

        // 회원 생성 및 저장
        Member member = Member.create(request.getUserId(), passwordEncoder.encode(request.getPassword1()));
        Member savedMember = memberRepository.save(member);

        return MemberResponse.of(savedMember);
    }
}
