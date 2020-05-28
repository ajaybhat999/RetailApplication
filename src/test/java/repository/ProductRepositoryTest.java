package repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.example.myretail.model.ItemDetails;
import com.example.myretail.model.ProductApiResponse;
import com.example.myretail.model.ProductDescription;
import com.example.myretail.model.ProductDetails;
import com.example.myretail.repository.ProductRepositoryImpl;
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
import org.springframework.web.client.RestTemplate;

/**
 * Created by akrish10 on 5/28/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {

    @Mock
    RestTemplate productRestTemplate;

    @InjectMocks
    ProductRepositoryImpl productRepository;



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
        assertEquals(productApiResponse.getProduct().getItem().getTcin(),"1234567");
        assertEquals(productApiResponse.getProduct().getItem().getProduct_description().getTitle(),"test title");
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
