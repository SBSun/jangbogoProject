package backend.jangbogoProject.entity.category.repository;

import backend.jangbogoProject.entity.category.Category;
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
    public Category findByName(String name) {
        return queryFactory
                .selectFrom(category)
                .where(toEq(category.name, name))
                .fetchOne();
    }

    @Override
    public Category findByCategoryId(Long categoryId) {
        return queryFactory
                .selectFrom(category)
                .where(toEq(category.id, categoryId))
                .fetchOne();
    }

    @Override
    public List<Category> findByDepth(int depth) {
        return queryFactory
                .selectFrom(category)
                .where(toEq(category.depth, depth))
                .fetch();
    }

    @Override
    public Long findIdByName(String name) {
        return queryFactory
                .select(category.id)
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

    @Override
    public List<CategoryResponseDto> findSubCategoriesByName(String name) {

        String sql = "WITH RECURSIVE subcategories AS (" +
                " SELECT c.*" +
                " FROM category c" +
                " WHERE name = :name" +
                " UNION ALL" +
                " SELECT c.*" +
                " FROM category c" +
                " JOIN subcategories sub" +
                " ON c.parent_id = sub.category_id" +
                ")" +
                " SELECT sub.category_id as id, sub.name, sub.parent_id as parentId, sub.depth" +
                " FROM subcategories sub";

        Query q = em.createNativeQuery(sql).setParameter("name", name);
        List<Object[]> resultList = q.getResultList();
        List<CategoryResponseDto> subCategories = resultList.stream()
                .map(child -> new CategoryResponseDto(
                        ((Integer) child[0]).longValue(),
                        (String) child[1],
                        ((Integer) child[2]).longValue(),
                        (Integer) child[3]
                )).collect(Collectors.toList());

        return subCategories;
    }
}
