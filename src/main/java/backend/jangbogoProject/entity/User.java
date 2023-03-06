package backend.jangbogoProject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "login_type")
    private String loginType;
    @Column(nullable = false)
    private String authority;

    @Builder
    public User(Long id, String email, String password, String name, String loginType, String authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.loginType = loginType;
        this.authority = authority;
    }

    public void update(String password, String name){
        this.password = password;
        this.name = name;
    }
}
