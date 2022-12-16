package com.iqbalfa.electronic.service.interfaces;

import com.iqbalfa.electronic.model.Category;

import java.util.List;

public interface ICategoryService {
    Category create(Category category) throws Exception;

    List<Category> list() throws Exception;

    Category getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    Category update(Long id, Category category) throws Exception;

}
