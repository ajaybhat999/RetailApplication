package com.example.myretail.repository;

import com.example.myretail.model.ProductApiResponse;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductRepository {

    ProductApiResponse getProductDetails(String productId);
}
