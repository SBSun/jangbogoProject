package backend.jangbogoProject.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    

    @Builder
    public User(String id, String password, String name, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
