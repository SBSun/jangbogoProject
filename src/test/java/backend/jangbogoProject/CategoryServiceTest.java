package backend.jangbogoProject;

import backend.jangbogoProject.category.Category;
import backend.jangbogoProject.category.CategoryDTO;
import backend.jangbogoProject.category.CategoryRepository;
import backend.jangbogoProject.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    private CategoryDTO createCategoryDTO(String testBranch, String testCode, String testName) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch(testBranch);
        categoryDTO.setCode(testCode);
        categoryDTO.setName(testName);
        return categoryDTO;
    }

    private Category findCategory (int savedId) {
        return categoryRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void 카테고리_저장_테스트 () {
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestCode", "TestName");
        int savedId = categoryService.saveCategory(categoryDTO);

        //when
        Category category = findCategory(savedId);

        //then
        assertThat(category.getCode()).isEqualTo("TestCode");
    }
}
