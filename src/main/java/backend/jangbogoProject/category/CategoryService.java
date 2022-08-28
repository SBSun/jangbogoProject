package backend.jangbogoProject.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public int saveCategory (CategoryDTO categoryDTO) {
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

        }
        //중, 소분류 등록
        else {
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

    // 대분류를 return 하여 하위 모든 카테고리를 가져오도록
    public Map<String, CategoryDTO> getCategoryByBranch(String branch){
        Category category = categoryRepository.findByBranchAndName(branch, "ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다."));

        CategoryDTO categoryDTO = new CategoryDTO(category);

        Map<String, CategoryDTO> data = new HashMap<>();
        data.put(categoryDTO.getName(), categoryDTO);

        return data;
    }

    public Category findCategory(int categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("CategoryId에 해당하는 카테고리 없음"));

        return category;
    }

    public void deleteCategory(int categoryId){
        Category category = findCategory(categoryId);

        // 하위 카테고리가 없을 경우
        if(category.getSubCategory().size() == 0){
            Category parentCategory = findCategory(category.getParentCategory().getId());
            if(!parentCategory.getName().equals("ROOT")){
                parentCategory.getSubCategory().remove(category);
            }
            categoryRepository.deleteById(category.getId());
        }else{ // 하위 카테고리가 있을 경우
            Category parentCategory = findCategory(category.getParentCategory().getId());
            if(!parentCategory.getName().equals("ROOT")){
                parentCategory.getSubCategory().remove(category);
            }
            category.setName("Deleted Category");
        }
    }
}
