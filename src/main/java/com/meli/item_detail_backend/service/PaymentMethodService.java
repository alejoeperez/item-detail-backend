package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethod> getAllPaymentMethods();
}
