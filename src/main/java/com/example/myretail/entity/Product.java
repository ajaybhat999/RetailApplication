package com.example.myretail.entity;

import com.example.myretail.entity.Price;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by akrish10 on 5/26/20.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {
    private String id;
    private String name;
    private Price current_price;
}
