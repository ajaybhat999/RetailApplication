package com.example.myretail.service;

import com.example.myretail.model.Products;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductService {

    Products getProductDetails(String productId);

    void updateProductPrice(Products productRequest);

    void createProductPrice(Products productRequest);
}
