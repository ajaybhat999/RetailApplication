package com.example.myretail.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by akrish10 on 5/27/20.
 */
@Table("price")
@Getter
@Setter
@Builder
public class PriceMapper {
    @PrimaryKeyColumn(name = "productid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    String productid;

    @Column("currency")
    String currency;

    @Column("price")
    String price;
}
