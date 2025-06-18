package com.meli.item_detail_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item_detail_backend.dto.OrderRequest;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.service.OrderRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRequestController.class)
@AutoConfigureMockMvc
public class OrderRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRequestService orderRequestService;

    @Test
    public void createOrderRequest_OK() throws Exception{

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductId("p1");
        orderRequest.setVariantId("v1");
        orderRequest.setSize("8");
        orderRequest.setQuantity(1);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value("Order processed successfully"));

        // Verify the service method was called
        verify(orderRequestService).processOrder(any(OrderRequest.class));

    }

    @Test
    public void createOrderRequest_BusinessError() throws Exception{

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductId("p1");
        orderRequest.setVariantId("v1");
        orderRequest.setSize("8");
        orderRequest.setQuantity(1);

        doThrow(new BusinessException("Bad Request", HttpStatus.BAD_REQUEST))
                .when(orderRequestService).processOrder(orderRequest);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderRequest)))
                .andExpect(status().isBadRequest());

        // Verify the service method was called
        verify(orderRequestService).processOrder(any(OrderRequest.class));

    }
}
