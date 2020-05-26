package com.example.myretail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * Created by akrish10 on 5/26/20.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {
    private String id;
    private String name;
    private Price current_price;
}
