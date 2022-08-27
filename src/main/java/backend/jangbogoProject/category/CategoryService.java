package backend.jangbogoProject.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long saveCategory (CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity();
//대분류 등록
        if (categoryDTO.getParentCategoryName() == null) {

            //JPA 사용하여 DB에서 branch와 name의 중복값을 검사. (대분류에서만 가능)
            if (categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName())) {
                throw new RuntimeException("branch와 name이 같을 수 없습니다. ");
            }
//orElse로 refactor
            Category rootCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(),"ROOT")
                    .orElseGet( () ->
                            Category.builder()
                                    .name("ROOT")
                                    .code("ROOT")
                                    .branch(categoryDTO.getBranch())
                                    .level(0)
                                    .build()
                    );
            if (!categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), "ROOT")) {
                categoryRepository.save(rootCategory);
            }
            category.setParentCategory(rootCategory);
            category.setLevel(1);
            //중, 소분류 등록
        } else {
            String parentCategoryName = categoryDTO.getParentCategoryName();
            Category parentCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(), parentCategoryName)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));
            category.setLevel(parentCategory.getLevel() + 1);
            category.setParentCategory(parentCategory);
            parentCategory.getSubCategory().add(category);
        }

        //category.setLive(true);
        return categoryRepository.save(category).getId();
    }
}
