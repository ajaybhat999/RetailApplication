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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Value("${product.rest.getProductsUrl:https://redsky.target.com/v2/pdp/tcin/{productId}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics}")
    private String getProductsUrl;


    @Override
    public List<Product> getProductDetails(String productId) {
        log.debug("Inside repository");
        ResponseEntity<ProductResponse> response = null;
        try {
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("productId", productId);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getProductsUrl);
            String urlString = builder.buildAndExpand(urlParams).toUriString();
            response = productRestTemplate.exchange(urlString, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ProductResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {
                return null;
            }
        } catch (Exception ex) {
            if(response.getStatusCode().is5xxServerError()){
                log.debug("Internal server error");
            } else if(response.getStatusCode().is4xxClientError()){
                log.debug("4xx error");
            }
        }
        return null;
    }

}
