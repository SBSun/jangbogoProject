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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /*
    @Transactional
    public void create(CategoryRequestDTO categoryRequestDTO){
        if(categoryRepository.existsByName(categoryRequestDTO.getName())){
            throw new RestApiException(CommonErrorCode.ALREADY_SAVED_CATEGORY);
        }

        Category category;

        // 상위 카테고리가 없다면 대분류로 등록
        if(categoryRequestDTO.getParentName() == null){
            Category rootCategory = categoryRepository.findByName("ROOT");
            if(rootCategory == null){
                rootCategory = Category.builder()
                        .name("ROOT")
                        .depth(0)
                        .build();
            }

            // ROOT 카테고리가 없는 상황에서 상위 카테고리가 없는 카테고리를 등록하려 한다면 ROOT 카테고리 생성
            if(!categoryRepository.existsByName("ROOT")){
                categoryRepository.save(rootCategory);
            }

            category = Category.builder()
                    .name(categoryRequestDTO.getName())
                    .parent(rootCategory)
                    .depth(rootCategory.getDepth() + 1)
                    .build();
        }else{
            String parentName = categoryRequestDTO.getParentName();

            Category parent = categoryRepository.findByName(parentName);
            if(parent == null){
                throw new RestApiException(CommonErrorCode.PARENT_CATEGORY_NOT_FOUND);
            }

            category = Category.builder()
                    .name(categoryRequestDTO.getName())
                    .parent(parent)
                    .depth(parent.getDepth() + 1)
                    .build();

            parent.getChildren().add(category);
        }

        categoryRepository.save(category);
    }

    public CategoryResponseDto.Info findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND));

        CategoryResponseDto.Info info = CategoryResponseDto.Info.of(category);

        return info;
    }

    public CategoryResponseDto.Info findAll(){
        Category category = categoryRepository.findById(1L)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND));

        CategoryResponseDto.Info info = CategoryResponseDto.Info.of(category);

        return info;
    }


    public Long findIdByName(String name){
        Category category = categoryRepository.findByName(name);
        if(category == null){
            new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND);
        }

        return category.getId();
    }*/

    public CategoryResponseDto createCategoryRoot(){
        Map<Long, List<CategoryResponseDto>> groupingByParent = categoryRepository.findAll().stream()
                .map(c -> new CategoryResponseDto(c.getId(), c.getName(), c.getParentId(), c.getDepth()))
                .collect(Collectors.groupingBy(p -> p.getParentId()));

        CategoryResponseDto rootCategoryDto = new CategoryResponseDto(1l, "ROOT", null, 0);
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

    public CategoryResponseDto findByName(String name){
        // exists 함수 구현하기

        return categoryRepository.findByName(name);
    }

    public List<String> findNamesByDepth(int depth){

        return categoryRepository.findNamesByDepth(depth);
    }
}
