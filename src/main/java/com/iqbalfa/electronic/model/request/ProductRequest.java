package com.iqbalfa.electronic.model.request;

import com.iqbalfa.electronic.model.Category;
import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private CategoryIdRequest category;
    private Integer price;
}
