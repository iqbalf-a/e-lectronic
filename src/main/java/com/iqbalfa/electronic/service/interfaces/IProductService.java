package com.iqbalfa.electronic.service.interfaces;

import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.model.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    Product create(Product product) throws Exception;

    List<Product> list() throws Exception;

    Product getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    Product update(Long id, Product product) throws Exception;
}
