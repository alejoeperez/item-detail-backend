package com.meli.item_detail_backend.dto;

import lombok.Data;

@Data
public class PaymentOption {
    private String name;      // Ej: "Bancolombia", "Visa"
    private String logoUrl;   // Ruta a la imagen
}
