package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.entity.user.QUser;
import backend.jangbogoProject.entity.user.User;
import backend.jangbogoProject.entity.user.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class UserRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    private void init(){
        user = getUser();
        em.persist(user);
    }

    @Test
    @DisplayName("이메일 존재 여부")
    public void existsByEmail(){

        boolean exists = userRepository.existsByEmail(user.getEmail());

        // then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("이메일로 User 조회")
    public void findByEmail(){

        // when
        User user1 = userRepository.findByEmail(user.getEmail());

        // then
        assertThat(user1.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("User 정보 수정")
    @Transactional
    public void updateUser(){
        // given
        QUser qUser = QUser.user;

        String updateName = "Update Name";
        String updatePassword = "Update Password";

        // when
        queryFactory.update(qUser)
                .where(qUser.email.eq(user.getEmail()))
                .set(qUser.name, updateName)
                .set(qUser.password, updatePassword)
                .execute();

        em.clear();

        User user1 = queryFactory.selectFrom(qUser)
                .where(qUser.email.eq(user.getEmail()))
                .fetchOne();

        // then
        assertThat(user1.getName()).isEqualTo(updateName);
        assertThat(user1.getPassword()).isEqualTo(updatePassword);
    }

    @Test
    @DisplayName("User 삭제")
    @Transactional
    public void deleteUser(){
        // given
        QUser qUser = QUser.user;

        // when
        queryFactory.delete(qUser)
                .where(qUser.email.eq(user.getEmail()))
                .execute();

        em.clear();

        Integer fetchOne = queryFactory.selectOne()
                        .from(qUser)
                        .where(qUser.email.eq(user.getEmail()))
                        .fetchFirst();

        // then
        assertThat(fetchOne).isNull();
    }

    private User getUser(){
        String email = "Test Email";
        String password = "Test Password";
        String name = "Test Name";
        String loginType = "Test LoginType";
        String authority = "Test Authority";
        return  User.builder()
                .email(email)
                .password(password)
                .name(name)
                .loginType(loginType)
                .authority(authority)
                .build();
    }
}
