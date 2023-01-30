package backend.jangbogoProject.repositoryTest;

import backend.jangbogoProject.user.Authority;
import backend.jangbogoProject.user.User;
import backend.jangbogoProject.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("사용자 추가")
    @Test
    void addUser(){
        // given
        User user = user();

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser.getId()).isEqualTo(user.getId());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(savedUser.getAddress()).isEqualTo(user.getAddress());
        assertThat(savedUser.getAuthority()).isEqualTo(user.getAuthority());
    }

    private User user() {
        return User.builder()
                .id("test@test.test")
                .password("test Password")
                .name("test Name")
                .address("test Address")
                .authority(Authority.USER.getValue())
                .build();
    }
}
