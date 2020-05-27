package com.example.myretail.controller;

import com.example.myretail.model.ProductDetails1;
import com.example.myretail.model.ProductResponse;
import com.example.myretail.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<ProductDetails1> getProductDetails(@PathVariable(value = "productId") String productId) {
        log.debug("Inside ProductDetails1 controller: getProduct()");
        ProductResponse productResponse = productService.getProductDetails(productId);
        return new ResponseEntity(productResponse, HttpStatus.OK);

    }
}
