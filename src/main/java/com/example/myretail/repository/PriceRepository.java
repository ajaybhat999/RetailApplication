package com.example.myretail.repository;

import com.example.myretail.model.PriceMapper;

/**
 * Created by akrish10 on 5/26/20.
 */
public interface PriceRepository {

    PriceMapper getPriceDetails(String productId);
}
