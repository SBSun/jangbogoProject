package backend.jangbogoProject.member.dto;

import backend.jangbogoProject.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class MemberDto{

    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String tel;
    private Boolean enabled;

    public Member toEntity() {
        Member build = Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .address(address)
                .tel(tel)
                .enabled(enabled)
                .build();
        return build;
    }

    @Builder
    public MemberDto(Long id, String email, String password, String name, String address, String tel, Boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.enabled = enabled;
    }
}
