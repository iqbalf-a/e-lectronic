package com.iqbalfa.electronic.service;

import com.iqbalfa.electronic.exception.NotFoundException;
import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.model.Product;
import com.iqbalfa.electronic.repository.ICategoryRepository;
import com.iqbalfa.electronic.repository.IProductRepository;
import com.iqbalfa.electronic.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private IProductRepository productRepository;
    private ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product product) {
        Long categoryId = product.getCategory().getCategoryId();
        Optional<Category> category = categoryRepository.findByCategoryById(categoryId);
        product.setCategory(category.get());
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public List<Product> list() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product getById(Long id) throws Exception {
        Optional<Product> existingProduct = productRepository.findByProductId(id);
        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return existingProduct.get();
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Product> existingProduct = productRepository.findByProductId(id);
        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteProductById(id);
    }

    @Override
    public Product update(Long id, Product product) throws Exception {
        Optional<Product> existingProduct = productRepository.findByProductId(id);
        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " not found");
        }

        Long categoryId = product.getCategory().getCategoryId();
        Optional<Category> existingCategory = categoryRepository.findByCategoryById(id);
        if (existingCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " not found");
        }
        product.setCategory(existingCategory.get());
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

}
