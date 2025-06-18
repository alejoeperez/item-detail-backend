package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.Product;
import com.meli.item_detail_backend.dto.ProductVariant;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.serviceImp.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() throws Exception{
        // Inyectamos datos manualmente en lugar de depender del archivo JSON
        productService = new ProductServiceImpl();

        ProductVariant variant = new ProductVariant();
        variant.setId("v1");
        variant.setColor("Negro");
        variant.setSizeStock(Map.of("8", 10));
        variant.setOriginalPrice(100000);
        variant.setDiscountPercentage(10.0);
        variant.setImages(List.of("img1.jpg"));

        Product product = new Product();
        product.setId("p1");
        product.setTitle("Producto Test");
        product.setVariants(List.of(variant));

        // Simulamos que el PostConstruct ya inyectó estos productos
        productService = new ProductServiceImpl() {{
            // Sobrescribimos directamente el campo privado

                var field = ProductServiceImpl.class.getDeclaredField("products");
                field.setAccessible(true);
                field.set(this, List.of(product));

        }};
    }

    @Test
    public void getAllProducts_OK() {
        List<Product> products = productService.getAllProducts();
        Assertions.assertNotNull(products);
        Assertions.assertEquals(1, products.size());
    }

    @Test
    public void shouldReturnProductById() {
        Product product = productService.getProductById("p1");
        Assertions.assertNotNull(product);
        Assertions.assertEquals("p1", product.getId());
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFound() {
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            productService.getProductById("not-exists");
        });

        Assertions.assertEquals("Product with id not-exists not found", exception.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
