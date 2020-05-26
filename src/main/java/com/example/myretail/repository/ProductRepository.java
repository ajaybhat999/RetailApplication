package com.example.myretail.repository;

import com.example.myretail.model.Product;

import java.util.List;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductRepository {

    List<Product> getProductDetails(String productId);
}
