package backend.jangbogoProject;

import backend.jangbogoProject.category.Category;
import backend.jangbogoProject.category.CategoryDTO;
import backend.jangbogoProject.category.CategoryRepository;
import backend.jangbogoProject.category.CategoryService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    private CategoryDTO createCategoryDTO(String testBranch, String testName) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch(testBranch);
        categoryDTO.setName(testName);
        return categoryDTO;
    }

    private Category findCategory (int savedId) {
        return categoryRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void 카테고리_검색_테스트(){
        int categoryId = categoryRepository.findIdByBranchAndName("축산물", "ROOT");
        Category category = findCategory(categoryId);
        CategoryDTO categoryDTO = new CategoryDTO(category);
        Gson gson = new Gson();
        String json = gson.toJson(categoryDTO).toString();
        System.out.println(json);
    }

    @Test
    public void 카테고리_저장_테스트 () {
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestName");
        int savedId = categoryService.saveCategory(categoryDTO);

        //when
        Category category = findCategory(savedId);

        //then
        assertThat(category.getName()).isEqualTo("TestName");
    }
}
