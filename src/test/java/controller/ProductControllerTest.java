package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myretail.controller.ProductController;
import com.example.myretail.model.Price;
import com.example.myretail.model.Product;
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
public class ProductControllerTest {


    @InjectMocks
    ProductController productController;

    @Mock
    private ProductService productService;


    @Test
    public void shouldGetListOfBrandsOnValidProductId() {
        List<Product> productList = getMockProductList();
        when(productService.getProductDetails(anyString())).thenReturn(productList);
        ResponseEntity responseEntity = productController.getProductDetails("1234567");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    private List<Product> getMockProductList() {
        List<Product> productList = new ArrayList<>();
        Product product = Product
                .builder().id("1234567").name("Test Product").current_price(Price.builder().value
                ("10").currency_code("USD").build()).build();
        productList.add(product);
        return productList;
    }


}
