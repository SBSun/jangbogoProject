package backend.jangbogoProject.entity.user.repository;

import backend.jangbogoProject.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static backend.jangbogoProject.entity.user.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public User findByEmail(String email){
        return queryFactory.selectFrom(user)
                .where(user.email.eq(email))
                .fetchOne();
    }

    @Override
    public Long deleteByEmail(String email) {
        return queryFactory.delete(user)
                .where(user.email.eq(email))
                .execute();
    }
}
