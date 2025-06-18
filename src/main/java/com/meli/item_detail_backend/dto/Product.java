package com.meli.item_detail_backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Product {

    private String id;
    private String title;
    private String description;
    private List<ProductVariant> variants;
    private List<ProductFeatures> features;

}
