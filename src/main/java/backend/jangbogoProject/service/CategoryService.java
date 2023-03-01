package backend.jangbogoProject.service;

import backend.jangbogoProject.repository.CategoryRepository;
import backend.jangbogoProject.dto.CategoryRequestDTO;
import backend.jangbogoProject.dto.CategoryResponseDTO;
import backend.jangbogoProject.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(CategoryRequestDTO categoryRequestDTO){
        if(categoryRepository.existsByName(categoryRequestDTO.getName())){
            throw new IllegalArgumentException("해당 카테고리는 이미 존재합니다.");
        }

        Category category;

        // 상위 카테고리가 없다면 대분류로 등록
        if(categoryRequestDTO.getParentName() == null){
            Category rootCategory = categoryRepository.findByName("ROOT")
                    .orElseGet(() ->
                            Category.builder()
                                    .name("ROOT")
                                    .depth(0l)
                                    .build()
                    );

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
            Category parent = categoryRepository.findByName(parentName)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));

            category = Category.builder()
                    .name(categoryRequestDTO.getName())
                    .parent(parent)
                    .depth(parent.getDepth() + 1)
                    .build();

            parent.getChildren().add(category);
        }

        return categoryRepository.save(category).getId();
    }

    public CategoryResponseDTO findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(category);

        return categoryResponseDTO;
    }

    public CategoryResponseDTO findByName(String name){
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(category);

        return categoryResponseDTO;
    }

    public CategoryResponseDTO findAll(){
        Category category = categoryRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.of(category);

        return categoryResponseDTO;
    }

    public Long findIdByName(String name){
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));

        return category.getId();
    }
}