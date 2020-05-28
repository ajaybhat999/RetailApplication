package com.example.myretail.controller;

import com.example.myretail.model.Product;
import com.example.myretail.model.ProductResponse;
import com.example.myretail.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@RestController
@RequestMapping("${microservice.contextPath}/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductDetails(@PathVariable(value = "productId") String productId) {
        log.debug("Inside Product controller: getProduct()");
        ProductResponse productResponse = productService.getProductDetails(productId);
        return new ResponseEntity(productResponse, HttpStatus.OK);
    }

    @PutMapping("/product")
    public ResponseEntity updateProductDetails(@RequestBody ProductResponse productRequest){
        log.debug("Inside Product Controller: update product()");
        productService.updateProductPrice(productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity createProductDetails(@RequestBody ProductResponse productRequest){
        log.debug("Inside Product Controller: create product price()");
        productService.createProductPrice(productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
