package backend.jangbogoProject.dto;

import backend.jangbogoProject.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

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

    public MemberDto()
    {
        super();
    }

    @Builder
    public MemberDto(Long id, String email, String password, String name, String address, String tel, Boolean enabled) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.enabled = enabled;
    }

}
