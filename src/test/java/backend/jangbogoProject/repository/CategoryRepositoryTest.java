package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class CategoryRepositoryTest {

    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("새 카테고리 저장")
    public void saveCategory(){
        // given
        Category category = createCategory();

        // when
        Category savedCategory = categoryRepository.save(category);

        // then
        assertThat(category.getId()).isEqualTo(savedCategory.getId());
        assertThat(category.getName()).isEqualTo(savedCategory.getName());
        assertThat(category.getParentId()).isEqualTo(savedCategory.getParentId());
        assertThat(category.getDepth()).isEqualTo(savedCategory.getDepth());
    }
    
    @Test
    @DisplayName("카테고리 이름으로 해당 카테고리 반환")
    public void findByName(){
        // given
        Category savedCategory = categoryRepository.save(createCategory());

        // when
        Category findCategory = categoryRepository.findByName(savedCategory.getName());

        // then
        assertThat(findCategory.getId()).isEqualTo(savedCategory.getId());
        assertThat(findCategory.getName()).isEqualTo(savedCategory.getName());
        assertThat(findCategory.getParentId()).isEqualTo(savedCategory.getParentId());
        assertThat(findCategory.getDepth()).isEqualTo(savedCategory.getDepth());
    }
    
    @Test
    @DisplayName("카테고리 Id로 해당 카테고리 반환")
    public void findByCategoryId(){
        // given
        Category savedCategory = categoryRepository.save(createCategory());

        // when
        Category findCategory = categoryRepository.findByCategoryId(savedCategory.getId());

        // then
        assertThat(findCategory.getId()).isEqualTo(savedCategory.getId());
        assertThat(findCategory.getName()).isEqualTo(savedCategory.getName());
        assertThat(findCategory.getParentId()).isEqualTo(savedCategory.getParentId());
        assertThat(findCategory.getDepth()).isEqualTo(savedCategory.getDepth());
    }

    @Test
    @DisplayName("카테고림 이름으로 해당 카테고리 Id 반환")
    public void findIdByName(){
        // given
        Category savedCategory = categoryRepository.save(createCategory());
        String name = "조미료";

        // when
        Long categoryId = categoryRepository.findIdByName(name);

        // then
        assertThat(categoryId).isEqualTo(savedCategory.getId());
        assertThat(name).isEqualTo(savedCategory.getName());
    }

    @Test
    @DisplayName("특정 Depth에 해당하는 카테고리들의 이름 반환")
    public void findNamesByDepth(){
        // given
        List<Category> createCategories = createCategories();
        for (int i = 0; i < createCategories.size(); i++) {
            categoryRepository.save(createCategories.get(i));
        }
        int depth = 3;

        // when
        List<String> names = categoryRepository.findNamesByDepth(depth);

        // then
        assertThat(names.size()).isEqualTo(2);
        assertThat(names.get(0)).isEqualTo("진간장");
        assertThat(names.get(1)).isEqualTo("국간장");
    }

    @Test
    @DisplayName("카테고리 이름으로 하위 카테고리들 포함해서 반환")
    public void findSubCategoriesByName(){
        // given
        List<Category> createCategories = createCategories();
        for (int i = 0; i < createCategories.size(); i++) {
            categoryRepository.save(createCategories.get(i));
        }
        String categoryName = "조미료";

        // when
        List<CategoryResponseDto> categories = categoryRepository.findSubCategoriesByName(categoryName);

        // then
        assertThat(categories.size()).isEqualTo(createCategories.size());
    }
    
    private Category createCategory(){
        return new Category(1000l,"조미료", 0l, 1);
    }

    private List<Category> createCategories(){
        Category c1 = new Category(1000l, "조미료", 0l, 1);
        Category sub1 = new Category(c1.getId() + 1, "소금", c1.getId(), 2);
        Category sub2 = new Category(sub1.getId() + 1, "설탕", c1.getId(), 2);
        Category sub3 = new Category(sub2.getId() + 1, "간장", c1.getId(), 2);
        Category sub3_1 = new Category(sub3.getId() + 1, "진간장", sub3.getId(), 3);
        Category sub3_2 = new Category(sub3_1.getId() + 1, "국간장", sub3.getId(), 3);

        List<Category> categories = List.of(
            c1, sub1, sub2, sub3, sub3_1, sub3_2
        );

        return categories;
    }
}
