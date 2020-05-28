package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.myretail.model.ItemDetails;
import com.example.myretail.dto.PriceMapper;
import com.example.myretail.model.ProductApiResponse;
import com.example.myretail.model.ProductDescription;
import com.example.myretail.model.ProductDetails;
import com.example.myretail.model.Products;
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
        Products products = productService.getProductDetails("1234567");
        assertNotNull(products);
        assertEquals(products.getId(),"1234567");
        assertEquals(products.getName(),"Test Product Description");
        assertNotNull(products.getCurrent_price());
        assertEquals(products.getCurrent_price().getValue(),"12");
        assertEquals(products.getCurrent_price().getCurrency_code(),"USD");
    }

    @Test
    public void shouldFetchOnlyProductInformationOnValidRequestForProductAndInvalidPrice() {
        when(productRepository.getProductDetails(anyString())).thenReturn(mockProductResponse());
        Products products = productService.getProductDetails("1234567");
        assertNotNull(products);
        assertEquals(products.getId(),"1234567");
        assertEquals(products.getName(),"Test Product Description");
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
