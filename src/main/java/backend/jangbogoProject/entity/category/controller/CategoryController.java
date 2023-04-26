package backend.jangbogoProject.entity.category.controller;

import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    /*
    @PostMapping("/create")
    public void createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        categoryService.create(categoryRequestDTO);
    }

    */
    @GetMapping("/findAll")
    public CategoryResponseDto findAll(){
        return categoryService.createCategoryRoot();
    }

    @GetMapping("/findByName")
    public CategoryResponseDto findByName(@RequestParam @NotBlank String name){
        return categoryService.findByName(name);
    }
}
