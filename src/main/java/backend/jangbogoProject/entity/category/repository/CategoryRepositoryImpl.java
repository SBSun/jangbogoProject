package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static backend.jangbogoProject.entity.category.QCategory.category;
import static backend.jangbogoProject.utils.RepositorySupport.*;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    @Autowired
    private final JPAQueryFactory queryFactory;

    @Override
    public Category findByName(String name) {
        return queryFactory
                .select(category)
                .from(category)
                .where(toEq(category.name, name))
                .fetchOne();
    }

    @Override
    public List<String> findNamesByDepth(int depth) {
        return queryFactory
                .select(category.name)
                .from(category)
                .where(toEq(category.depth, depth))
                .fetch();
    }
}
