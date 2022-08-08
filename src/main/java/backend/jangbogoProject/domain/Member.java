package backend.jangbogoProject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// UserDetails - Spring Security에서 사용자의 정보를 담는 인터페이스이다
// 대부분의 경우 Spring Security의 기본 UserDetails로는 실무에서 필요한 정보를 모두 담을 수 없기에 아래와 같은 CustomUserDetails를 구현하여 사용
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String tel;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @Builder
    public Member(Long id, String address, String email, String name, String password, String tel, Boolean enabled) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.enabled = enabled;
    }
}
