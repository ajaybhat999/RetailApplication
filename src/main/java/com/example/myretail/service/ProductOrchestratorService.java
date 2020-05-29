package com.example.myretail.service;

import com.example.myretail.dto.Products;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductOrchestratorService {

    Products getProductDetails(String productId);

    void updateProductPrice(Products productRequest);

    void createProductPrice(Products productRequest);
}
