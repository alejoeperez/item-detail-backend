package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.dto.Product;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProductByIdOK() throws Exception{
        Product product = new Product();
        product.setId("p1");

        when(productService.getProductById("p1")).thenReturn(product);

        mockMvc.perform(get("/api/products/p1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("p1"));
    }

    @Test
    public void getProductById_BusinessException() throws Exception{

        when(productService.getProductById("not-found"))
                .thenThrow(new BusinessException("Product not found", HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/api/products/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value(404));
    }

    @Test
    public void getAllProductsOK() throws Exception{
        Product product = new Product();
        product.setId("p1");

        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value("p1"));
    }
}
