package com.iqbalfa.electronic.controller;

import com.iqbalfa.electronic.model.Product;
import com.iqbalfa.electronic.model.request.ProductRequest;
import com.iqbalfa.electronic.model.response.SuccessResponse;
import com.iqbalfa.electronic.service.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private IProductService productService;
    private ModelMapper modelMapper;

    public ProductController(IProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ResponseEntity getAllProducts() throws Exception {
        List<Product> result = productService.list();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        Product result = productService.getById(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get data", result));
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody ProductRequest productRequest) throws Exception {
        Product newProduct = modelMapper.map(productRequest, Product.class);
        Product result = productService.create(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) throws Exception {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Seccess deleted id " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) throws Exception {
        Product newProduct = modelMapper.map(productRequest, Product.class);
        Long newId = Long.valueOf(id);
        Product result = productService.update(newId, newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update product", result));
    }
}
