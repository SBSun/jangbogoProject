package backend.jangbogoProject.entity.category.controller;

import backend.jangbogoProject.entity.category.dto.CategoryRequestDTO;
import backend.jangbogoProject.entity.category.dto.CategoryResponseDTO;
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

    @PostMapping("/create")
    public Long createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        return categoryService.create(categoryRequestDTO);
    }

    @GetMapping("/findByName")
    public CategoryResponseDTO findByName(@RequestParam @NotBlank String name){
        return categoryService.findByName(name);
    }

    @GetMapping("/findAll")
    public CategoryResponseDTO findAll(){
        return categoryService.findAll();
    }
}
