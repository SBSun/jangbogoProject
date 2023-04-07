package backend.jangbogoProject.repository;

import backend.jangbogoProject.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static backend.jangbogoProject.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public User findByEmail(String email){
        return queryFactory.selectFrom(user)
                .where(user.email.eq(email))
                .fetchOne();
    }
}
