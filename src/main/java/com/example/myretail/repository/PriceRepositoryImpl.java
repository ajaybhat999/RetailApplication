package com.example.myretail.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.myretail.dto.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by akrish10 on 5/27/20.
 */
@Repository
@Slf4j
public class PriceRepositoryImpl implements PriceRepository {


    public static final String PRICE_TABLE =
            "price";


    @Autowired
    CassandraOperations cassandraOperations;

    @Override
    public PriceMapper getPriceDetails(String productId) {
        Select stmt = QueryBuilder.select().from(PRICE_TABLE);
        stmt.where(QueryBuilder.eq("productId", productId));
        PriceMapper priceMapper = cassandraOperations.selectOne(stmt, PriceMapper.class);
        return priceMapper;
    }

    @Override
    public void updateProductDetails(PriceMapper priceMapper) {
        cassandraOperations.insert(priceMapper);
    }
}
