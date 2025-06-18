package com.meli.item_detail_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ProductVariant {

    private String id;
    private String color;
    private Map<String, Integer> sizeStock;
    private double originalPrice;
    private double discountPercentage;
    private List<String> images;

    @JsonProperty("finalPrice")
    public double getFinalPrice() {
        return originalPrice * (1 - (discountPercentage / 100.0));
    }
}
