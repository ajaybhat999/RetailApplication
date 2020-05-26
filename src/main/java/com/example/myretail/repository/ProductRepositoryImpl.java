package com.example.myretail.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    @Override
    public void getProductDetails(String productId) {
        log.debug("Inside repository");
    }
}
