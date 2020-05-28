package com.example.myretail.service;

import com.example.myretail.model.ProductResponse;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductService {

    ProductResponse getProductDetails(String productId);

    void updateProductPrice(ProductResponse productRequest);

    void createProductPrice(ProductResponse productRequest);
}
