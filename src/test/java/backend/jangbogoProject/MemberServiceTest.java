package backend.jangbogoProject;

import backend.jangbogoProject.domain.Member;
import backend.jangbogoProject.repository.MemberRepository;
import backend.jangbogoProject.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 회원_비밀번호수정_테스트(){
        String email = "byung0216@naver.com";
        String newPassword = "1234";

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(newPassword);

        Member testMember1 = memberService.findEmail(email).get();
        System.out.println(passwordEncoder.matches(newPassword, testMember1.getPassword()));

        String res = memberService.updatePassword(member);

        Member testMember2 = memberService.findEmail(email).get();
        System.out.println(passwordEncoder.matches(newPassword, testMember2.getPassword()));
    }

    @Test
    public void 회원_인적사항수정_테스트(){
        String email = "byung0216@naver.com";
        String newName = "SBSun";
        String newAddress = "서울시 도봉구 창동";
        String newTel = "010 4444 4444";

        Gson gson = new Gson();
        String json;
        Member testMember1 = memberService.findEmail(email).get();
        System.out.println(testMember1.getEmail() + ", " + testMember1.getName());

        Member member = new Member();
        member.setEmail(email);
        member.setName(newName);
        member.setAddress(newAddress);
        member.setTel(newTel);

        String res = memberService.updateOtherInfo(member);

        json = gson.toJson(member).toString();
        System.out.println(json);
    }
}
