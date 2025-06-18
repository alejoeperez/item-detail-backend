package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.common.ApiResponse;
import com.meli.item_detail_backend.dto.OrderRequest;
import com.meli.item_detail_backend.service.OrderRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
@Tag(name = "Orders", description = "Operations related with orders")
public class OrderRequestController {

    private final OrderRequestService orderRequestService;

    //inject by constructor
    public OrderRequestController(OrderRequestService orderRequestService) {
        this.orderRequestService = orderRequestService;
    }

    @PostMapping
    @Operation(summary = "Create a new Order Request", description = "Creates a new Order Request that includes product, variant, size and quantity info")
    public ResponseEntity<ApiResponse<String>> createOrderRequest(@RequestBody OrderRequest request) {
        orderRequestService.processOrder(request);
        ApiResponse<String> response = new ApiResponse<>("Order processed successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
