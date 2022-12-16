package com.iqbalfa.electronic.controller;

import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.model.request.CategoryIdRequest;
import com.iqbalfa.electronic.model.request.CategoryRequest;
import com.iqbalfa.electronic.model.response.SuccessResponse;
import com.iqbalfa.electronic.service.interfaces.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private ICategoryService categoryService;
    private ModelMapper modelMapper;

    public CategoryController(ICategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ResponseEntity getAllCategories() throws Exception {
        List<Category> result = categoryService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        Category result = categoryService.getById(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get data", result));
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest) throws Exception {
        Category newCategory = modelMapper.map(categoryRequest, Category.class);
        Category result = categoryService.create(newCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create category", result));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        categoryService.delete(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete category", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest) throws Exception {
        Category newCategory = modelMapper.map(categoryRequest, Category.class);
        Long newId = Long.valueOf(id);
        Category result = categoryService.update(newId, newCategory);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update category", result));
    }
}
