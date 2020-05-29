package com.example.myretail.service;

import com.example.myretail.entity.ProductApiResponse;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductService {

    ProductApiResponse getProductDetails(String productId);
}
