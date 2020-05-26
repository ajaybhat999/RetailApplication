package com.example.myretail.service;

import com.example.myretail.model.Product;
import com.example.myretail.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProductDetails(String productId) {
        log.debug("Inside Service: getProductDetails");
        productRepository.getProductDetails(productId);

        return null;
    }
}
