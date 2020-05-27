package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myretail.model.ItemDetails;
import com.example.myretail.model.PriceMapper;
import com.example.myretail.model.ProductApiResponse;
import com.example.myretail.model.ProductDescription;
import com.example.myretail.model.ProductDetails;
import com.example.myretail.model.ProductResponse;
import com.example.myretail.repository.PriceRepository;
import com.example.myretail.repository.ProductRepository;
import com.example.myretail.service.ProductServiceImpl;
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
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    ProductServiceImpl productService;


    @Test
    public void shouldFetchProductInformationOnValidRequestForPriceAndProduct() {
        when(productRepository.getProductDetails(anyString())).thenReturn(mockProductResponse());
        when(priceRepository.getPriceDetails(anyString())).thenReturn(mockPricingResponse());
        ProductResponse productResponse = productService.getProductDetails("1234567");
        assertNotNull(productResponse);
        assertEquals(productResponse.getId(),"1234567");
        assertEquals(productResponse.getName(),"Test Product Description");
        assertNotNull(productResponse.getCurrent_price());
        assertEquals(productResponse.getCurrent_price().getValue(),"12");
        assertEquals(productResponse.getCurrent_price().getCurrency_code(),"USD");
    }

    @Test
    public void shouldFetchOnlyProductInformationOnValidRequestForProductAndInvalidPrice() {
        when(productRepository.getProductDetails(anyString())).thenReturn(mockProductResponse());
        ProductResponse productResponse = productService.getProductDetails("1234567");
        assertNotNull(productResponse);
        assertEquals(productResponse.getId(),"1234567");
        assertEquals(productResponse.getName(),"Test Product Description");
        assertNull(productResponse.getCurrent_price());
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
