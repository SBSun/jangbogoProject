package backend.jangbogoProject.entity.category.service;

import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDTO;
import backend.jangbogoProject.entity.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

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

    public CategoryResponseDTO findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND));

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(category);

        return categoryResponseDTO;
    }

    public CategoryResponseDTO findByName(String name){
        Category category = categoryRepository.findByName(name);
        if(category == null){
            throw new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND);
        }

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(category);

        return categoryResponseDTO;
    }

    public CategoryResponseDTO findAll(){
        Category category = categoryRepository.findById(1L)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND));

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(category);

        return categoryResponseDTO;
    }

    public Long findIdByName(String name){
        Category category = categoryRepository.findByName(name);
        if(category == null){
            new RestApiException(CommonErrorCode.CATEGORY_NOT_FOUND);
        }

        return category.getId();
    }

    public List<String> findNamesByDepth(int depth){

        return categoryRepository.findNamesByDepth(depth);
    }
}
