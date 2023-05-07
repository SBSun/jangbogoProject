package backend.jangbogoProject.entity.category.controller;

import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDto;
import backend.jangbogoProject.entity.category.service.CategoryService;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/categories")
    public void create(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        categoryService.create(categoryRequestDTO);
    }

    @GetMapping("/categories")
    public CategoryResponseDto findAll(){
        return categoryService.createCategoryRoot();
    }

    @GetMapping("/categories/{name}")
    public CategoryResponseDto findSubCategoriesByName(@PathVariable String name){
        return categoryService.findSubCategoriesByName(name);
    }
}
