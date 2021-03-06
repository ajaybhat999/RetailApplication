package repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import com.example.myretail.exception.ErrorResponse;
import com.example.myretail.exception.RetailApplicationException;
import com.example.myretail.entity.ItemDetails;
import com.example.myretail.entity.ProductApiResponse;
import com.example.myretail.entity.ProductDescription;
import com.example.myretail.entity.ProductDetails;
import com.example.myretail.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by akrish10 on 5/28/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    RestTemplate productRestTemplate;

    @InjectMocks
    ProductServiceImpl productRepository;



    @Before
    public void setup(){
        ReflectionTestUtils.setField(productRepository,"getProductsUrl","testUrl");
    }


    @Test
    public void shouldFetchProductDetailsIfDetailsAreAvailableInApi(){
        ResponseEntity<ProductApiResponse> mockResponse = mock(ResponseEntity.class);
        doReturn(mockResponse).when(productRestTemplate).exchange(any(String.class),
                eq(HttpMethod.GET), any(),
                any(ParameterizedTypeReference.class));
        doReturn(HttpStatus.OK).when(mockResponse).getStatusCode();
        doReturn(getMockProductApiResponse()).when(mockResponse).getBody();
        ProductApiResponse productApiResponse = productRepository.getProductDetails("1234567");
        assertNotNull(productApiResponse);
        assertEquals("1234567",productApiResponse.getProduct().getItem().getTcin());
        assertEquals("test title",productApiResponse.getProduct().getItem().getProduct_description().getTitle());
    }


    @Test(expected = RetailApplicationException.class)
    public void shouldThrowAuthoringExceptionWhenHttpClientErrorOnGetProductAttributes()
            throws RetailApplicationException, IOException {
        ResponseEntity<String> mockResponse = mock(ResponseEntity.class);
        HttpClientErrorException mockHttpClientErrorException =
                mock(HttpClientErrorException.class);
        doReturn(HttpStatus.INTERNAL_SERVER_ERROR).when(mockResponse).getStatusCode();
        doReturn(HttpStatus.INTERNAL_SERVER_ERROR).
                when(mockHttpClientErrorException).getStatusCode();
        doReturn("").when(mockHttpClientErrorException).getResponseBodyAsString();
        ErrorResponse responseObject = new ErrorResponse("UNKNOWN", "UN known");
        doThrow(mockHttpClientErrorException).when(productRestTemplate)
                .exchange(any(String.class), eq(HttpMethod.GET),
                        eq(null),
                        any(ParameterizedTypeReference.class));
        productRepository.getProductDetails("1234567");
    }



    private Object getMockProductApiResponse() {
        return ProductApiResponse.builder().product(getMockProductDetails()).build();
    }

    private ProductDetails getMockProductDetails() {
        return ProductDetails.builder().item(getMockItemDetails()).build();

    }

    private ItemDetails getMockItemDetails() {
        return ItemDetails.builder().product_description(getMockProductDescription()).tcin("1234567").build();
    }

    private ProductDescription getMockProductDescription() {
        return ProductDescription.builder().title("test title").build();
    }


}
