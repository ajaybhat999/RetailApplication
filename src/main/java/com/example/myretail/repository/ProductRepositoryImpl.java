package com.example.myretail.repository;

import com.example.myretail.model.Product;
import com.example.myretail.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    @Qualifier("productRestTemplate")
    private RestTemplate productRestTemplate;

    //To-Do: Replace url params
    @Value("${product.rest.getProductsUrl:https://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price," +
            "promotion," +
            "bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics}")
    private String getProductsUrl;


    @Override
    public List<Product> getProductDetails(String productId) {
        log.debug("Inside repository");
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("productId", productId);
            ResponseEntity<ProductResponse> response = null;
            response = productRestTemplate.exchange(getProductsUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ProductResponse>() {
                    },params);
            if (response.getStatusCode().is2xxSuccessful()) {
                return null;
            }
        } catch (Exception ex) {
            HttpStatus httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            if (ex instanceof HttpStatusCodeException) {
                HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
                httpStatusCode = httpException.getStatusCode();
            }
            log.error("Exception while calling segregate entity service", ex.getMessage());
            if (httpStatusCode.value() == 404) {
                log.debug("No Eligible entity present");
            }
        }
        return null;
    }

}
