package backend.jangbogoProject.service;

import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import backend.jangbogoProject.entity.category.service.CategoryService;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.errorCode.ErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Long id = 1l;
    private String name = "조미료";
    private Long parentId = 0l;
    private int depth = 1;

    // region 카테고리 등록 테스트
    @Test
    public void 대분류카테고리등록(){
        // given
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("조미료", "");
        Category category = new Category(id,  name, parentId, depth);
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
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("소금", "조미료");
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
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("소금", "조미료");
        doReturn(true).when(categoryRepository).existsByName(categoryRequestDTO.getName());

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.create(categoryRequestDTO));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.ALREADY_SAVED_CATEGORY);
    }

    @Test
    public void 카테고리등록실패_상위카테고리가존재하지않음(){
        // given
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("소금", "조미료");
        doReturn(null).when(categoryRepository).findByName(categoryRequestDTO.getParentName());

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.create(categoryRequestDTO));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.PARENT_CATEGORY_NOT_FOUND);
    }
    // endregion

    // region 카테고리 찾기 테스트
    @Test
    public void 아이디로카테고리찾기_성공(){
        // given
        doReturn(Optional.of(new Category(id, name, parentId, depth))).when(categoryRepository).findById(id);

        // when
        Category category = categoryService.findById(id);

        // then
        assertThat(category.getId()).isEqualTo(id);
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getParentId()).isEqualTo(parentId);
    }

    @Test
    public void 아이디로카테고리찾기_실패(){
        // given
        doReturn(Optional.empty()).when(categoryRepository).findById(id);

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.findById(id));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.CATEGORY_NOT_FOUND);
    }

    @Test
    public void 이름으로카테고리찾기_성공(){
        // given
        doReturn(new Category(id, name, parentId, depth)).when(categoryRepository).findByName(name);

        // when
        Category category = categoryService.findByName(name);

        // then
        assertThat(category.getId()).isEqualTo(id);
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getParentId()).isEqualTo(parentId);
    }

    @Test
    public void 이름으로카테고리찾기_실패(){
        // given
        doReturn(null).when(categoryRepository).findByName(name);

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.findByName(name));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.CATEGORY_NOT_FOUND);
    }

    @Test
    public void Depth로카테고리목록찾기(){
        // given
        doReturn(createCategories()).when(categoryRepository).findByDepth(depth);

        // when
        List<Category> result = categoryService.findByDepth(depth);

        // then
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    public void 이름으로카테고리번호찾기_성공(){
        // given
        doReturn(id).when(categoryRepository).findIdByName(name);

        // when
        Long result = categoryService.findIdByName(name);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(id);
    }

    @Test
    public void 이름으로카테고리번호찾기_실패(){
        // given
        doReturn(null).when(categoryRepository).findIdByName(name);

        // when
        final RestApiException result = assertThrows(RestApiException.class, () -> categoryService.findIdByName(name));

        // then
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.CATEGORY_NOT_FOUND);
    }

    // endregion

    // region 계층형 카테고리 반환 테스트
    @Test
    public void Root_카테고리_생성(){
        // given
        doReturn(createCategories()).when(categoryRepository).findAll();

        // when
        CategoryResponseDto rootCategory = categoryService.createCategoryRoot();

        // then
        assertThat(rootCategory.getSubCategories().size()).isEqualTo(1);
        assertThat(rootCategory.getSubCategories().get(0).getSubCategories().size()).isEqualTo(3);
        assertThat(rootCategory.getSubCategories().get(0).getSubCategories().get(2).getSubCategories().size()).isEqualTo(2);
    }

    @Test
    public void 이름으로카테고리를찾아서_하위카테고리까지반환(){
        // given
        List<CategoryResponseDto> responseDtoList = createCategories().stream()
                .map(c -> new CategoryResponseDto(c.getId(), c.getName(), c.getParentId(), c.getDepth()))
                .collect(Collectors.toList());
        doReturn(responseDtoList).when(categoryRepository).findSubCategoriesByName(name);

        // when
        CategoryResponseDto findCategory = categoryService.findSubCategoriesByName(name);

        // then
        assertThat(findCategory.getId()).isEqualTo(id);
        assertThat(findCategory.getSubCategories().size()).isEqualTo(3);
        assertThat(findCategory.getSubCategories().get(2).getSubCategories().size()).isEqualTo(2);
    }

    // endregion

    private Category category() {
        return Category.builder()
                .name("소금")
                .parentId(1l)
                .depth(2)
                .build();
    }

    private List<Category> createCategories(){
        Category c1 = new Category(1l, "조미료", 0l, 1);
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
