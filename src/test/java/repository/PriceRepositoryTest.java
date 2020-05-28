package repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.datastax.driver.core.querybuilder.Select;
import com.example.myretail.model.PriceMapper;
import com.example.myretail.repository.PriceRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.cassandra.core.CassandraOperations;

/**
 * Created by akrish10 on 5/28/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceRepositoryTest {

    @Mock
    CassandraOperations cassandraOperations;

    @InjectMocks
    PriceRepositoryImpl priceRepository;


    @Test
    public void shouldFetchPriceIfEntryIsAvailableForProduct(){
        PriceMapper priceMapper = PriceMapper.builder().productid("1234567").price("12").currency("USD").build();
        when(cassandraOperations.selectOne(any(Select.class), any()))
                .thenReturn(priceMapper);
        PriceMapper resultPriceMapper = priceRepository.getPriceDetails("1234567");
        assertNotNull(resultPriceMapper);
        assertEquals(resultPriceMapper.getPrice(),"12");
        assertEquals(resultPriceMapper.getCurrency(),"USD");
        assertEquals(resultPriceMapper.getProductid(),"1234567");
    }

    @Test
    public void shouldNotFetchPriceIfPriceNotAvailable(){
        when(cassandraOperations.selectOne(any(Select.class), any()))
                .thenReturn(null);
        PriceMapper resultPriceMapper = priceRepository.getPriceDetails("1234567");
        assertNull(resultPriceMapper);
    }

}
