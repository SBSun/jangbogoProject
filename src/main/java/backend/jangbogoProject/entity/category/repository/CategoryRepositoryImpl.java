package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.QCategory;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static backend.jangbogoProject.entity.category.QCategory.category;
import static backend.jangbogoProject.utils.RepositorySupport.*;
import static com.querydsl.sql.SQLExpressions.select;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    @Autowired
    private final EntityManager em;

    @Autowired
    private final JPAQueryFactory queryFactory;

    @Override
    public CategoryResponseDto findByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public CategoryResponseDto findByName(String name) {


        return null;
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
