package com.meli.item_detail_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String productId;
    private String variantId;
    private String size;
    private int quantity;

    @Override
    public String toString() {
        return "OrderRequest{" +
                "productId='" + productId + '\'' +
                ", variantId='" + variantId + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
