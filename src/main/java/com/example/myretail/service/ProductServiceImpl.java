package com.example.myretail.service;

import com.example.myretail.exception.RetailApplicationException;
import com.example.myretail.model.Price;
import com.example.myretail.dto.PriceMapper;
import com.example.myretail.model.ProductApiResponse;
import com.example.myretail.model.Products;
import com.example.myretail.repository.PriceRepository;
import com.example.myretail.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Products getProductDetails(String productId) {
        log.debug("Inside Service: getProductDetails");
        ProductApiResponse productResponseFromApi = productRepository.getProductDetails(productId);
        String productNumber = productResponseFromApi.getProduct().getItem().getTcin();
        String productDescription = productResponseFromApi.getProduct().getItem().getProduct_description()
                .getTitle();
        PriceMapper priceMapper = priceRepository.getPriceDetails(productId);
        Products products = new Products();
        products.setId(productNumber);
        products.setName(productDescription);
        if (null != priceMapper) {
            Price price = new Price();
            price.setValue(priceMapper.getPrice());
            price.setCurrency_code(priceMapper.getCurrency());
            products.setCurrent_price(price);
        }
        return products;
    }

    @Override
    public void updateProductPrice(Products productRequest) {
        String productId = productRequest.getId();
        log.debug("Updating price for product ID {}",productId);
        PriceMapper priceMapper = priceRepository.getPriceDetails(productId);
        if(null != priceMapper){
            priceRepository.updateProductDetails(getPriceMapperFromRequest(productRequest));
        } else {
            throw new RetailApplicationException("No product found with product ID", HttpStatus.NOT_FOUND.toString(),
                    HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void createProductPrice(Products productRequest) {
        priceRepository.updateProductDetails(getPriceMapperFromRequest(productRequest));
    }

    private PriceMapper getPriceMapperFromRequest(Products products) {
        PriceMapper priceMapper = PriceMapper.builder().productid(products.getId()).currency(products
                .getCurrent_price().getCurrency_code()).price(products.getCurrent_price().getValue()).build();
        return priceMapper;
    }


}
