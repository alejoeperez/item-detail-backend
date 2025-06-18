package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.OrderRequest;

public interface OrderRequestService {

    void processOrder(OrderRequest request);
}
