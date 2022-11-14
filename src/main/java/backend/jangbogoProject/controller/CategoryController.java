package backend.jangbogoProject.controller;

import backend.jangbogoProject.service.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
}
