package config;

import static org.junit.Assert.assertNotNull;

import com.example.myretail.config.RestTemplateConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by akrish10 on 5/27/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class RestTemplateConfigurationTest {


    @InjectMocks
    RestTemplateConfiguration restTemplateConfiguration;


    @Before
    public void init(){
        ReflectionTestUtils.setField(restTemplateConfiguration,"maxTotalConnections",5);
        ReflectionTestUtils.setField(restTemplateConfiguration,"readTimeout",500);
        ReflectionTestUtils.setField(restTemplateConfiguration,"connectionTimeout",5);
    }

    @Test
    public void shouldInitializeRestTemplate(){
        RestTemplate restTemplate=restTemplateConfiguration.productRestTemplate();
        assertNotNull(restTemplate);
    }
}
