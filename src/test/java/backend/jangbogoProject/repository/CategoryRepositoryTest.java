package backend.jangbogoProject.repository;

import backend.jangbogoProject.TestConfig;
import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class CategoryRepositoryTest {

    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private CategoryRepository categoryRepository;

    /*
    @Test
    @DisplayName("카테고리 이름으로 으로 카테고리 정보 반환")
    public void findByName(){
        // given
        String categoryName = "수산물";

        // when
        CategoryResponseDto.Info info = categoryRepository.findByName(categoryName);

        // then

    }*/

    @Test
    @DisplayName("특정 Depth에 해당하는 카테고리들의 이름 반환")
    public void findNamesByDepth(){
        // given
        int depth = 2;

        // when
        List<String> names = categoryRepository.findNamesByDepth(depth);

        // then
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }
    }
}
