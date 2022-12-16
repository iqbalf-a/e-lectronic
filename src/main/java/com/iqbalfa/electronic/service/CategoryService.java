package com.iqbalfa.electronic.service;

import com.iqbalfa.electronic.exception.EntityExistException;
import com.iqbalfa.electronic.exception.NotFoundException;
import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.repository.ICategoryRepository;
import com.iqbalfa.electronic.service.interfaces.ICategoryService;
import com.iqbalfa.electronic.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private ICategoryRepository categoryRepository;
    private IProductService productService;

    public CategoryService(ICategoryRepository categoryRepository, IProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Category create(Category category) {
        try {
            Category newCategory = categoryRepository.save(category);
            return newCategory;
        } catch (Exception e) {
            throw new EntityExistException("Category " + category.getCategoryName() + " already exist");
        }
    }

    @Override
    public List<Category> list() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @Override
    public Category getById(Long id) throws Exception {
        Optional<Category> existingCategory = categoryRepository.findByCategoryById(id);
        if (existingCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " not found");
        }
        return existingCategory.get();
    }

    @Override
    public void delete(Long id) throws Exception {
        productService
                .list()
                .stream()
                .filter(c -> c
                        .getCategory()
                        .getCategoryId()
                        .equals(id))
                .forEach(p -> {
                            p.setCategory(null);
                            try {
                                productService.update(p.getProductId(), p);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        Optional<Category> existingCategory = categoryRepository.findByCategoryById(id);
        if (existingCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " not found");
        }
        categoryRepository.deleteCategoryById(id);
    }

    @Override
    public Category update(Long id, Category category) throws Exception {
        Optional<Category> existingCategory = categoryRepository.findByCategoryById(id);
        if (existingCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " not found");
        }
        category.setCategoryId(id);
        Category newCategory = categoryRepository.save(category);
        return newCategory;
    }


}
