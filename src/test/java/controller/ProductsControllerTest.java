package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myretail.controller.ProductsController;
import com.example.myretail.entity.Price;
import com.example.myretail.dto.Products;
import com.example.myretail.service.ProductOrchestratorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by akrish10 on 5/26/20.
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {


    @InjectMocks
    ProductsController productsController;

    @Mock
    private ProductOrchestratorService productOrchestratorService;


    @Test
    public void shouldGetListOfProductPricesOnValidProductId() {
        Products productList = getMockProductResponse();
        when(productOrchestratorService.getProductDetails(anyString())).thenReturn(productList);
        ResponseEntity responseEntity = productsController.getProductDetails("1234567");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    private Products getMockProductResponse() {
        Price price = new Price();
        price.setCurrency_code("USD");
        price.setValue("12");
        Products product = Products
                .builder().id("1234567").name("Test Product").current_price(price).build();
        return product;
    }


}
