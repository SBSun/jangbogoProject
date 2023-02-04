package backend.jangbogoProject.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    // SavedID
    private CategoryRequestDTO categoryRequestDTO(String name, String parent) {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO(name, parent);
        
        return categoryRequestDTO;
    }

    // Find Category
    private CategoryResponseDTO findCategory(Long saveId){
        return categoryService.findCategory(saveId);
    }

    @Test
    void 카테코리_생성_테스트() {
        // given
        CategoryRequestDTO categoryRequestDTO = categoryRequestDTO("TestName", null);
        Long saveId = categoryService.createCategory(categoryRequestDTO);

        // when
        CategoryResponseDTO categoryResponseDTO = findCategory(saveId);

        // then
        assertThat(categoryResponseDTO.getName()).isEqualTo("TestName");
    }
}