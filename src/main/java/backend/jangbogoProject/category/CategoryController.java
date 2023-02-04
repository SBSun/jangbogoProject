package backend.jangbogoProject.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("addCategory")
    public Long addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryService.saveCategory(categoryRequestDTO);
    }
}
