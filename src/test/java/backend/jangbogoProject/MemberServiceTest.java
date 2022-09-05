package backend.jangbogoProject;

import backend.jangbogoProject.category.CategoryRepository;
import backend.jangbogoProject.category.CategoryService;
import backend.jangbogoProject.market.MarketRepository;
import backend.jangbogoProject.member.domain.Member;
import backend.jangbogoProject.member.repository.MemberRepository;
import backend.jangbogoProject.member.service.MemberService;
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
    public void 회원_수정_테스트(){
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
}
