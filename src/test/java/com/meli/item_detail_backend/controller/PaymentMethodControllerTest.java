package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.dto.PaymentMethod;
import com.meli.item_detail_backend.service.PaymentMethodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentMethodController.class)
@AutoConfigureMockMvc
public class PaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentMethodService paymentMethodService;

    @Test
    public void getAllPaymentMethods_OK() throws Exception{

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId("pm1");

        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(paymentMethod);

        when(paymentMethodService.getAllPaymentMethods()).thenReturn(paymentMethods);

        mockMvc.perform(get("/api/payment-methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value("pm1"));
    }
}
