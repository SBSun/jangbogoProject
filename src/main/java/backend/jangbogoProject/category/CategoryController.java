package backend.jangbogoProject.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public Long createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryService.createCategory(categoryRequestDTO);
    }

    @GetMapping("/getCategoryByName")
    public CategoryResponseDTO getCategoryByName(String name){
        return categoryService.getCategoryByName(name);
    }
}
