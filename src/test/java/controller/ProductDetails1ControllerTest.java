package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myretail.controller.ProductController;
import com.example.myretail.model.Price;
import com.example.myretail.model.ProductDetails1;
import com.example.myretail.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ProductDetails1ControllerTest {


    @InjectMocks
    ProductController productController;

    @Mock
    private ProductService productService;


    @Test
    public void shouldGetListOfBrandsOnValidProductId() {
        List<ProductDetails1> productDetails1List = getMockProductList();
        when(productService.getProductDetails(anyString())).thenReturn(productDetails1List);
        ResponseEntity responseEntity = productController.getProductDetails("1234567");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    private List<ProductDetails1> getMockProductList() {
        List<ProductDetails1> productDetails1List = new ArrayList<>();
        ProductDetails1 productDetails1 = ProductDetails1
                .builder().id("1234567").name("Test ProductDetails1").current_price(Price.builder().value
                ("10").currency_code("USD").build()).build();
        productDetails1List.add(productDetails1);
        return productDetails1List;
    }


}
