package com.meli.item_detail_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Seller {

    private String id;
    private String name;
    private int totalProducts;
    private int sales;

    @JsonProperty("isMercadoLider")
    private boolean mercadoLider;

    private String leaderComment;
    private int progress;
    private List<String> badges;

}
