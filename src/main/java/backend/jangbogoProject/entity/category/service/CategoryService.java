package backend.jangbogoProject.entity.category.service;

import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;


    @Transactional
    public Category create(CategoryRequestDTO categoryRequestDTO){
        if(categoryRepository.existsByName(categoryRequestDTO.getName())){
            throw new RestApiException(CommonErrorCode.ALREADY_SAVED_CATEGORY);
        }

        Category category;

        // 상위 카테고리가 없다면 대분류로 등록
        if(!StringUtils.hasText(categoryRequestDTO.getParentName())){

            category = Category.builder()
                    .name(categoryRequestDTO.getName())
                    .parentId(0l)
                    .depth(1)
                    .build();
        }else{
            String parentName = categoryRequestDTO.getParentName();

            Category parentCategory = categoryRepository.findByName(parentName);

            if(parentCategory == null){
                throw new RestApiException(CommonErrorCode.PARENT_CATEGORY_NOT_FOUND);
            }

            category = Category.builder()
                    .name(categoryRequestDTO.getName())
                    .parentId(parentCategory.getId())
                    .depth(parentCategory.getDepth() + 1)
                    .build();
        }

        return categoryRepository.save(category);
    }

    public Category findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND));

        return category;
    }

    public Category findByName(String name){
        Category category = categoryRepository.findByName(name);
        if(category == null){
            new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND);
        }

        return category;
    }

    public List<Category> findByDepth(int depth){
        List<Category> categories = categoryRepository.findByDepth(depth);
        if(categories.isEmpty()){
            new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND);
        }

        return categories;
    }

    public Long findIdByName(String name){
        Long id = categoryRepository.findIdByName(name);

        if(id == null){
            new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND);
        }

        return id;
    }

    public CategoryResponseDto findSubCategoriesByName(String name){
        // exists 함수 구현하기
        List<CategoryResponseDto> categories = categoryRepository.findSubCategoriesByName(name);
        // 상위 카테고리를 List에서 꺼내온 후 제거
        CategoryResponseDto parentCategory = categories.get(0);
        categories.remove(0);

        Map<Long, List<CategoryResponseDto>> groupingByParent = categories.stream()
                .collect(Collectors.groupingBy(p -> p.getParentId()));

        addSubCategories(parentCategory, groupingByParent);
        return parentCategory;
    }

    public List<String> findNamesByDepth(int depth){

        return categoryRepository.findNamesByDepth(depth);
    }

    public CategoryResponseDto createCategoryRoot(){

        Map<Long, List<CategoryResponseDto>> groupingByParent = categoryRepository.findAll().stream()
                .map(c -> new CategoryResponseDto(c.getId(), c.getName(), c.getParentId(), c.getDepth()))
                .collect(Collectors.groupingBy(p -> p.getParentId()));

        CategoryResponseDto rootCategoryDto = new CategoryResponseDto(0l, "ROOT", null, 0);
        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
    }

    private void addSubCategories(CategoryResponseDto parent, Map<Long, List<CategoryResponseDto>> groupingByParentId){
        // 1. parent의 키로 subCategories를 찾는다.
        List<CategoryResponseDto> subCategories = groupingByParentId.get(parent.getId());

        // 종료 조건
        if(subCategories == null)
            return;

        // 2. sub categories 세팅
        parent.setSubCategories(subCategories);

        // 3. 재귀적으로 subcategories들에 대해서도 로직 수행
        subCategories.stream()
                .forEach(s ->{
                    addSubCategories(s, groupingByParentId);
                });

        // category의 하위 카테고리를 set하고, 다시 그 하위 카테고리에 차하위 카테고리를 set하는 식으로 동작
    }


}
