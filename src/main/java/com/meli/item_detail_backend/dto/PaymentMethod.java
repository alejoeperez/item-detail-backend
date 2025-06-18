package com.meli.item_detail_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentMethod {

    private String id;
    private String type; // "credit", "debit", "other"
    private String category; // "cash", "transfer" si type = "other"
    private String title; // for showing "Tarjetas de crédito", "Otros medios", etc.
    private List<PaymentOption> options;

}
