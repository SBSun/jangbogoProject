package backend.jangbogoProject.category;

import backend.jangbogoProject.dto.CategoryRequestDTO;
import backend.jangbogoProject.dto.CategoryResponseDTO;
import backend.jangbogoProject.service.CategoryService;
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

    // CreateId
    private CategoryRequestDTO categoryRequestDTO(String name, String parent) {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO(name, parent);
        
        return categoryRequestDTO;
    }

    @Test
    void 카테코리_생성_테스트() {
        // given
        CategoryRequestDTO categoryRequestDTO = categoryRequestDTO("TestName", null);
        Long saveId = categoryService.create(categoryRequestDTO);

        // when
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(saveId);

        // then
        assertThat(categoryResponseDTO.getName()).isEqualTo("TestName");
    }

    @Test
    void 카테고리_반환_테스트(){
        // given
        String name = "돼지고기";

        // when
        CategoryResponseDTO categoryResponseDTO = categoryService.findByName(name);

        // then
        System.out.println(categoryResponseDTO);
        assertThat(categoryResponseDTO.getName()).isEqualTo(name);
    }
}