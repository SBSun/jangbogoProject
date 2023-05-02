package backend.jangbogoProject.service;

import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import backend.jangbogoProject.entity.category.service.CategoryService;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    // region 카테고리 등록 테스트
    @Test
    public void 대분류카테고리등록(){
        // given
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("채소", "");
        Category category = new Category(2l, "채소", 0l, 1);
        doReturn(false).when(categoryRepository).existsByName(categoryRequestDTO.getName());
        doReturn(category).when(categoryRepository).save(any(Category.class));

        // when
        Category result = categoryService.create(categoryRequestDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(categoryRequestDTO.getName());

        verify(categoryRepository, times(1)).existsByName(categoryRequestDTO.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }
    
    @Test
    public void 하위카테고리등록(){
        // given
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("감자", "채소");
        doReturn(false).when(categoryRepository).existsByName(categoryRequestDTO.getName());
        doReturn(Category.builder().build()).when(categoryRepository).findByName(categoryRequestDTO.getParentName());
        doReturn(category()).when(categoryRepository).save(any(Category.class));

        // when
        Category result = categoryService.create(categoryRequestDTO);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(categoryRequestDTO.getName());
    }

    @Test
    public void 카테고리등록실패_이미존재(){
        // given
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("감자", "채소");
        doReturn(true).when(categoryRepository).existsByName(categoryRequestDTO.getName());

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.create(categoryRequestDTO));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.ALREADY_SAVED_CATEGORY);
    }

    @Test
    public void 카테고리등록실패_상위카테고리가존재하지않음(){
        // given
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("감자", "채소");
        doReturn(null).when(categoryRepository).findByName(categoryRequestDTO.getParentName());

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.create(categoryRequestDTO));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.PARENT_CATEGORY_NOT_FOUND);
    }
    // endregion

    

    private Category category() {
        return Category.builder()
                .name("감자")
                .parentId(2l)
                .depth(2)
                .build();
    }
}
