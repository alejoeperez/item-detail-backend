package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.dto.Seller;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.service.SellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SellerController.class)
@AutoConfigureMockMvc
public class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SellerService sellerService;

    @Test
    public void getSellerById_OK() throws Exception{

        Seller seller = new Seller();
        seller.setId("s1");

        when(sellerService.getSellerById("s1")).thenReturn(seller);

        mockMvc.perform(get("/api/sellers/s1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("s1"));
    }

    @Test
    public void getSellerById_BusinessException() throws Exception{

        when(sellerService.getSellerById("not-found"))
                .thenThrow(new BusinessException("Seller not found", HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/api/sellers/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value(404));
    }
}
