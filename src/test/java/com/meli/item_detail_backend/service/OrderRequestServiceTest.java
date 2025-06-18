package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.OrderRequest;
import com.meli.item_detail_backend.dto.Product;
import com.meli.item_detail_backend.dto.ProductVariant;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.serviceImp.OrderRequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class OrderRequestServiceTest {

    private ProductService productService;
    private OrderRequestServiceImpl orderService;

    @BeforeEach
    void setUp() {
        productService = Mockito.mock(ProductService.class);
        orderService = new OrderRequestServiceImpl(productService);
    }

    @Test
    void processOrder_shouldReduceStock_whenValidOrder() {
        // Arrange
        OrderRequest request = new OrderRequest();
        request.setProductId("p1");
        request.setVariantId("v1");
        request.setSize("8");
        request.setQuantity(2);

        Map<String, Integer> stock = new HashMap<>();
        stock.put("8", 5);

        ProductVariant variant = new ProductVariant();
        variant.setId("v1");
        variant.setSizeStock(stock);

        Product product = new Product();
        product.setId("p1");
        product.setVariants(List.of(variant));

        Mockito.when(productService.getProductById("p1")).thenReturn(product);

        // Act
        orderService.processOrder(request);

        // Assert
        Assertions.assertEquals(3, variant.getSizeStock().get("8"));
    }

    @Test
    void processOrder_shouldThrow_whenVariantNotFound() {
        OrderRequest request = new OrderRequest("p1", "v1", "8", 1);

        Product product = new Product();
        product.setId("p1");
        product.setVariants(Collections.emptyList());

        Mockito.when(productService.getProductById("p1")).thenReturn(product);

        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> orderService.processOrder(request));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        Assertions.assertTrue(ex.getMessage().contains("Variant with id v1 not found"));
    }

    @Test
    void processOrder_shouldThrow_whenSizeUnavailable() {
        OrderRequest request = new OrderRequest("p1", "v1", "9", 1);

        Map<String, Integer> stock = new HashMap<>();
        stock.put("8", 3);

        ProductVariant variant = new ProductVariant();
        variant.setId("v1");
        variant.setSizeStock(stock);

        Product product = new Product();
        product.setId("p1");
        product.setVariants(List.of(variant));

        Mockito.when(productService.getProductById("p1")).thenReturn(product);

        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> orderService.processOrder(request));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        Assertions.assertTrue(ex.getMessage().contains("Size 9 not available"));
    }

    @Test
    void processOrder_shouldThrow_whenStockInsufficient() {
        OrderRequest request = new OrderRequest("p1", "v1", "8", 5);

        Map<String, Integer> stock = new HashMap<>();
        stock.put("8", 2);

        ProductVariant variant = new ProductVariant();
        variant.setId("v1");
        variant.setSizeStock(stock);

        Product product = new Product();
        product.setId("p1");
        product.setVariants(List.of(variant));

        Mockito.when(productService.getProductById("p1")).thenReturn(product);

        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> orderService.processOrder(request));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        Assertions.assertTrue(ex.getMessage().contains("Stock insufficient"));
    }

}
