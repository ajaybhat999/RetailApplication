package com.example.myretail.service;

import com.example.myretail.model.Product;

import java.util.List;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface ProductService {

    List<Product> getProductDetails(String productId);

}
