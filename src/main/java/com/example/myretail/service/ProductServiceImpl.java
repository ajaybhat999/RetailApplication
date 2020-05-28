package com.example.myretail.service;

import com.example.myretail.exception.RetailApplicationException;
import com.example.myretail.model.Price;
import com.example.myretail.model.PriceMapper;
import com.example.myretail.model.ProductApiResponse;
import com.example.myretail.model.ProductResponse;
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
    public ProductResponse getProductDetails(String productId) {
        log.debug("Inside Service: getProductDetails");
        ProductApiResponse productResponseFromApi = productRepository.getProductDetails(productId);
        String productNumber = productResponseFromApi.getProduct().getItem().getTcin();
        String productDescription = productResponseFromApi.getProduct().getItem().getProduct_description()
                .getTitle();
        PriceMapper priceMapper = priceRepository.getPriceDetails(productId);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productNumber);
        productResponse.setName(productDescription);
        if (null != priceMapper) {
            Price price = new Price();
            price.setValue(priceMapper.getPrice());
            price.setCurrency_code(priceMapper.getCurrency());
            productResponse.setCurrent_price(price);
        }
        return productResponse;
    }

    @Override
    public void updateProductPrice(ProductResponse productRequest) {
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
    public void createProductPrice(ProductResponse productRequest) {
        priceRepository.updateProductDetails(getPriceMapperFromRequest(productRequest));
    }

    private PriceMapper getPriceMapperFromRequest(ProductResponse productResponse) {
        PriceMapper priceMapper = PriceMapper.builder().productid(productResponse.getId()).currency(productResponse
                .getCurrent_price().getCurrency_code()).price(productResponse.getCurrent_price().getValue()).build();
        return priceMapper;
    }


}
