package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.common.ApiResponse;
import com.meli.item_detail_backend.dto.PaymentMethod;
import com.meli.item_detail_backend.service.PaymentMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@CrossOrigin(origins = "*")
@Tag(name = "Payment Methods", description = "Operations related with payment methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    //injection by constructor
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    @Operation(summary = "Get All Payment Methods", description = "Return the complete list of payment methods")
    public ResponseEntity<ApiResponse<List<PaymentMethod>>> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
        ApiResponse<List<PaymentMethod>> response = new ApiResponse<>(paymentMethods);
        return ResponseEntity.ok(response);
    }
}
