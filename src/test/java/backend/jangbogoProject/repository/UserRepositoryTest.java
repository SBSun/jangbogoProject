package backend.jangbogoProject.repository;

import backend.jangbogoProject.constant.Authority;
import backend.jangbogoProject.constant.LoginType;
import backend.jangbogoProject.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 테스트 메서드가 하나 실행 후 종료되면 데이터가 초기화된다. - Transactional 어노테이션
    // auto_increment 값은 초기화가 안된다.

    @BeforeEach
    public void 데이터준비(){
        String email = "Test Email";
        String password = "Test Password";
        String name = "Test Name";
        String loginType = "Test LoginType";
        String authority = "Test Authority";
        User user =  User.builder()
                .email(email)
                .password(password)
                .name(name)
                .loginType(loginType)
                .authority(authority)
                .build();

        userRepository.save(user);
    }
    // 하나의 트랜잭션으로 처리 [ 데이터준비 + 유저 등록 ], [ 데이터준비, 유저 조회]

    @Test
    public void 유저_등록_테스트(){
        // given
        String email = "Save Email";
        String password = "Save Password";
        String name = "Save Name";
        String loginType = "Save LoginType";
        String authority = "Save Authority";
        User user =  User.builder()
                .email(email)
                .password(password)
                .name(name)
                .loginType(loginType)
                .authority(authority)
                .build();

        // when
        User userPS = userRepository.save(user);

        // then
        assertThat(email).isEqualTo(userPS.getEmail());
        assertThat(password).isEqualTo(userPS.getPassword());
    } // 트랜잭션 종료 (저장된 데이터를 초기화함)

    @Test
    public void 모든_유저_조회_테스트(){
        // given
        String email = "Test Email";
        String password = "Test Password";

        // when
        List<User> usersPS = userRepository.findAll();

        // then
        assertThat(email).isEqualTo( usersPS.get(usersPS.size() - 1).getEmail());
        assertThat(password).isEqualTo( usersPS.get(usersPS.size() - 1).getPassword());
    }

    @Test
    public void 유저_조회_테스트(){
        // given
        String email = "Test Email";
        String password = "Test Password";

        // when
        User userPS = userRepository.findByEmail(email).get();

        // then
        assertThat(email).isEqualTo(userPS.getEmail());
        assertThat(password).isEqualTo(userPS.getPassword());
    }

    @Test
    public void 유저_삭제_테스트(){
        // given
        String email = "Test Email";

        // when
        userRepository.deleteByEmail(email);

        // then
        assertFalse(userRepository.findByEmail(email).isPresent());
    }

    @Test
    public void 유저_수정_테스트(){
        // given
        User user = userRepository.findByEmail("Test Email").get();
        user.update("Edit Password", "Edit Name");

        // when
        User userPS = userRepository.save(user);

        userRepository.findAll().stream()
                .forEach((user1 -> {
                    System.out.println(user1.getId());
                    System.out.println(user1.getEmail());
                    System.out.println(user1.getPassword());
                    System.out.println(user1.getName());
                }));
    }
}
