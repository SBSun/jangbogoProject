package backend.jangbogoProject.controller;

import backend.jangbogoProject.dto.CategoryRequestDTO;
import backend.jangbogoProject.dto.CategoryResponseDTO;
import backend.jangbogoProject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public Long createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryService.create(categoryRequestDTO);
    }

    @GetMapping("/findByName")
    public CategoryResponseDTO findByName(String name){
        return categoryService.findByName(name);
    }

    @GetMapping("/findAll")
    public CategoryResponseDTO findAll(){
        return categoryService.findAll();
    }
}
