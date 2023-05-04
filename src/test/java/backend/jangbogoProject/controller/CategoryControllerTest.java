package backend.jangbogoProject.controller;

import backend.jangbogoProject.config.SecurityConfiguration;
import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.controller.CategoryController;
import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.service.CategoryService;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private Gson gson = new Gson();

    @Test
    @WithMockUser
    @DisplayName("[GET] 카테고리 목록 조회")
    public void findAll() throws Exception {
        // given
        String url = "/categories/findAll";
        CategoryResponseDto responseDto = createRootCategory();
        doReturn(responseDto).when(categoryService).createCategoryRoot();

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0l))
                .andExpect(jsonPath("$.name").value("ROOT"));
    }

    @Test
    @WithMockUser
    @DisplayName("[GET] 카테고리의 하위 카테고리까지 조회")
    public void findSubCategoriesByName() throws Exception {
        // given
        String url = "/categories/findSubCategoriesByName";
        CategoryResponseDto responseDto = categoryResponseDto();
        doReturn(responseDto).when(categoryService).findSubCategoriesByName("채소");

        // when
        final ResultActions resultActions = mockMvc.perform(
                get(url)
                        .param("name", "채소")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.name").value("채소"));
    }

    @Test
    @WithMockUser
    @DisplayName("[GET][ERROR] 카테고리의 하위 카테고리까지 조회 / 존재하지 않는 카테고리")
    public void findSubCategoriesByName_NotFound() throws Exception {
        // given
        String url = "/categories/findSubCategoriesByName";
        doThrow(new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND))
                .when(categoryService)
                .findSubCategoriesByName("채소");

        // when
        final ResultActions resultActions = mockMvc.perform(
                get(url)
                        .param("name", "채소")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @DisplayName("[GET][ERROR] 카테고리의 하위 카테고리까지 조회 / 잘못된 파라미터")
    public void findSubCategoriesByName_INVALID_PARAMETER() throws Exception {
        // given
        String url = "/categories/findSubCategoriesByName";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get(url)
                        .param("name", "")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    @DisplayName("[POST] 카테고리 추가")
    public void create() throws Exception {
        // given
        String url = "/categories/create";
        CategoryRequestDTO requestDTO = categoryRequestDTO("감자", "채소");
        doReturn(Category.builder().build()).when(categoryService).create(requestDTO);

        // when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .content(gson.toJson(requestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        );

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("[POST][ERROR] 카테고리 추가 / 카테고리 중복")
    public void create_ALREADY_SAVED_CATEGORY() throws Exception {
        // given
        String url = "/categories/create";
        CategoryRequestDTO requestDTO = categoryRequestDTO("감자", "채소");
        doThrow(new RestApiException(CommonErrorCode.ALREADY_SAVED_CATEGORY))
                .when(categoryService)
                .create(any());

        // when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .content(gson.toJson(requestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        );

        // then
        resultActions.andExpect(status().isConflict());
    }

    @Test
    @WithMockUser
    @DisplayName("[POST][ERROR] 카테고리 추가 / 존재하지 않는 상위 카테고리 ")
    public void create_PARENT_CATEGORY_NOT_FOUND() throws Exception {
        // given
        String url = "/categories/create";
        CategoryRequestDTO requestDTO = categoryRequestDTO("감자", "채소");
        doThrow(new RestApiException(CommonErrorCode.PARENT_CATEGORY_NOT_FOUND))
                .when(categoryService)
                .create(any());

        // when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .content(gson.toJson(requestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        );

        // then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @DisplayName("[POST][ERROR] 카테고리 추가 / 잘못된 파라미터 ")
    public void create_INVALID_PARAMETER() throws Exception {
        // given
        String url = "/categories/create";

        // when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .content(gson.toJson(categoryRequestDTO(null, null)))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        );

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    private CategoryResponseDto createRootCategory(){
        return CategoryResponseDto.builder()
                .id(0l)
                .name("ROOT")
                .depth(0)
                .build();
    }

    private CategoryResponseDto categoryResponseDto(){
        return CategoryResponseDto.builder()
                .id(1l)
                .name("채소")
                .parentId(0l)
                .depth(1)
                .build();
    }

    private CategoryRequestDTO categoryRequestDTO(String name, String parentName){
        return new CategoryRequestDTO(name, parentName);
    }
}
