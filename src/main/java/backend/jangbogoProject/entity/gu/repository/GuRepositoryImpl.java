package backend.jangbogoProject.entity.gu.repository;

import backend.jangbogoProject.entity.gu.dto.GuResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static backend.jangbogoProject.entity.gu.QGu.gu;
import static backend.jangbogoProject.utils.RepositorySupport.*;

@RequiredArgsConstructor
@Repository
public class GuRepositoryImpl implements GuRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public String findNameById(Long id) {
        return queryFactory
                .select(gu.name)
                .from(gu)
                .where(toEq(gu.id, id))
                .fetchOne();
    }

    @Override
    public List<GuResponseDto.Info> findAllGuInfo() {
        return queryFactory
                .select(Projections.constructor(GuResponseDto.Info.class,
                        gu.id,
                        gu.name
                ))
                .from(gu)
                .fetch();
    }
}
