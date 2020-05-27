package com.example.myretail.service;

import com.example.myretail.model.Price;
import com.example.myretail.model.PriceMapper;
import com.example.myretail.model.ProductApiResponse;
import com.example.myretail.model.ProductResponse;
import com.example.myretail.repository.PriceRepository;
import com.example.myretail.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PriceRepository priceRepository;

    @Override
    public ProductResponse getProductDetails(String productId) {
        log.debug("Inside Service: getProductDetails");
        ProductApiResponse productApiResponseFromApi = productRepository.getProductDetails(productId);
        PriceMapper priceMapper = priceRepository.getPriceDetails(productId);
        ProductResponse productResponse = new ProductResponse();
        //to-do - see why serialization is not happening.
        productResponse.setId(productId);
        productResponse.setName("Test");
        Price price = new Price();
        price.setValue(priceMapper.getPrice());
        price.setCurrency_code(priceMapper.getCurrency());
        productResponse.setCurrent_price(price);
        return productResponse;
    }
}
