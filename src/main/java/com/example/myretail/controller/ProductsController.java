package com.example.myretail.controller;

import com.example.myretail.entity.Product;
import com.example.myretail.dto.Products;
import com.example.myretail.service.ProductOrchestratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("${microservice.contextPath}/product")
public class ProductsController {

    @Autowired
    ProductOrchestratorService productOrchestratorService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductDetails(@PathVariable(value = "productId") String productId) {
        log.debug("Inside Product controller: getProduct()");
        Products products = productOrchestratorService.getProductDetails(productId);
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity updateProductDetails(@RequestBody Products productRequest){
        log.debug("Inside Product Controller: update product()");
        productOrchestratorService.updateProductPrice(productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createProductDetails(@RequestBody Products productRequest){
        log.debug("Inside Product Controller: create product price()");
        productOrchestratorService.createProductPrice(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
