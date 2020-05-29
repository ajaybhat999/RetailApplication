package com.example.myretail.service;

import com.example.myretail.exception.RetailApplicationException;
import com.example.myretail.entity.ProductApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("productRestTemplate")
    private RestTemplate productRestTemplate;

    @Value("${product.rest.getProductsUrl:https://redsky.target.com/v2/pdp/tcin/{productId}?excludes=taxonomy,price," +
            "promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics}")
    private String getProductsUrl;


    @Override
    public ProductApiResponse getProductDetails(String productId) {
        log.debug("Inside repository");
        ResponseEntity<ProductApiResponse> response = null;
        try {
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("productId", productId);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getProductsUrl);
            String urlString = builder.buildAndExpand(urlParams).toUriString();
            response = productRestTemplate.exchange(urlString, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ProductApiResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {
                ProductApiResponse productApiResponse = response.getBody();
                return productApiResponse;
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().is5xxServerError()) {
                log.error("Internal server error");
                throw new RetailApplicationException(ex.getMessage(),ex.getStatusCode().toString(),ex.getStatusCode());
            } else if (ex.getStatusCode().is4xxClientError()) {
                log.error("Internal server error");
                throw new RetailApplicationException(ex.getMessage(),ex.getStatusCode().toString(),ex.getStatusCode());
            }
        }
        return null;
    }

}
