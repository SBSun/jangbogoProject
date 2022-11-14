package backend.jangbogoProject.service;

import backend.jangbogoProject.domain.Role;
import backend.jangbogoProject.repository.MemberRepository;
import backend.jangbogoProject.domain.Member;
import backend.jangbogoProject.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원가입
    public Member save(Member member)
    {
        //해당 email을 가진 회원이 있으면
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        member.setEnabled(true);
        
        // 권한을 1 - ROLE_MEMBER로 설정, 현재는 ROLE_MEMBER 이외의 권한이 없음.
        Role role = new Role();
        role.setId(1l);
        member.getRoles().add(role);
        memberRepository.save(member);
        return member;
    }
    
    @Transactional
    public String updatePassword(Member member){
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if(!findMember.isPresent())
        {
            return "존재하지 않는 이메일입니다.";
        }

        Member saveMember = findMember.get();
        if(StringUtils.isNotBlank(member.getPassword()))
        {
            saveMember.setPassword(passwordEncoder.encode(member.getPassword()));
        }

        return "비밀번호 수정 성공";
    }

    @Transactional
    public String updateOtherInfo(Member member){
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if(!findMember.isPresent())
        {
            return "존재하지 않는 이메일입니다.";
        }

        Member saveMember = findMember.get();

        if(StringUtils.isNotBlank(member.getName()))
        {
            saveMember.setName(member.getName());
        }
        if(StringUtils.isNotBlank(member.getAddress()))
        {
            saveMember.setAddress(member.getAddress());
        }
        if(StringUtils.isNotBlank(member.getTel()))
        {
            saveMember.setTel(member.getTel());
        }

        return "인적사항 수정 성공";
    }

    public Optional<Member> findEmail(String memberEmail){
        Optional<Member> member = memberRepository.findByEmail(memberEmail);

        if(member.isPresent()) {
            return member;
        }else{
            System.out.println("존재하지 않는 이메일입니다.");
            return null;
        }
    }

    public Optional<Member> findById(Long id){
        Optional<Member> member = memberRepository.findById(id);

        if(member.isPresent()) {
            return member;
        }else{
            System.out.println("존재하지 않는 이메일입니다.");
            return null;
        }
    }

    public int isCheckEmail(String memberEmail){
        Optional<Member> customMemberDetails = findEmail(memberEmail);

        // 저장값이 존재하지 않으면 0
        if(!customMemberDetails.isPresent()){
            System.out.println("해당 이메일 없음");
            return 0;
        }
        else{
            System.out.println("해당 이메일 존재" + customMemberDetails.get().getEmail());
            return 1;
        }
    }

    public boolean login(Member member){
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        //널 값이면 로그인 X
        if(!findMember.isPresent()){
            return false;
        }
        System.out.println(member.getPassword() + ", " + findMember.get().getPassword());
        //비밀번호 동일하지 않으면 로그인 X
        if(!passwordEncoder.matches(member.getPassword(), findMember.get().getPassword())){
            return false;
        }

        //로그인 가능
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);
        System.out.println("loadUserByUsername");
        if(member.isPresent())
            System.out.println(email + " 존재");
        else
        {
            throw new UsernameNotFoundException("Not Found Email");
        }

        return member.get();
    }
}
