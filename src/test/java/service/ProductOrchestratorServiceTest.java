package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myretail.entity.ItemDetails;
import com.example.myretail.model.PriceMapper;
import com.example.myretail.entity.ProductApiResponse;
import com.example.myretail.entity.ProductDescription;
import com.example.myretail.entity.ProductDetails;
import com.example.myretail.dto.Products;
import com.example.myretail.repository.PriceRepository;
import com.example.myretail.service.ProductService;
import com.example.myretail.service.ProductOrchestratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by akrish10 on 5/27/20.
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ProductOrchestratorServiceTest {

    @Mock
    ProductService productService;

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    ProductOrchestratorServiceImpl productOrchestratorService;


    @Test
    public void shouldFetchProductInformationOnValidRequestForPriceAndProduct() {
        when(productService.getProductDetails(anyString())).thenReturn(mockProductResponse());
        when(priceRepository.getPriceDetails(anyString())).thenReturn(mockPricingResponse());
        Products products = productOrchestratorService.getProductDetails("1234567");
        assertNotNull(products);
        assertEquals("1234567",products.getId());
        assertEquals("Test Product Description",products.getName());
        assertNotNull(products.getCurrent_price());
        assertEquals("12",products.getCurrent_price().getValue());
        assertEquals("USD",products.getCurrent_price().getCurrency_code());
    }

    @Test
    public void shouldFetchOnlyProductInformationOnValidRequestForProductAndInvalidPrice() {
        when(productService.getProductDetails(anyString())).thenReturn(mockProductResponse());
        Products products = productOrchestratorService.getProductDetails("1234567");
        assertNotNull(products);
        assertEquals("1234567",products.getId());
        assertEquals("Test Product Description",products.getName());
        assertNull(products.getCurrent_price());
    }

    private PriceMapper mockPricingResponse() {
        PriceMapper priceMapper = PriceMapper.builder().price("12").currency("USD").build();
        return priceMapper;
    }

    private ProductApiResponse mockProductResponse() {
        ProductDescription productDescription = ProductDescription.builder().title("Test Product Description").build();
        ItemDetails itemDetails = ItemDetails.builder().tcin("1234567").product_description(productDescription).build();
        ProductDetails productDetails = ProductDetails.builder().item(itemDetails).build();
        ProductApiResponse productApiResponse = ProductApiResponse.builder().product(productDetails).build();
        return productApiResponse;
    }


}
