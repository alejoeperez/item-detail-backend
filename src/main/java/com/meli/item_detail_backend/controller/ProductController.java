package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.common.ApiResponse;
import com.meli.item_detail_backend.dto.Product;
import com.meli.item_detail_backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "*")
@Tag(name = "Products", description = "Operations related with products")
public class ProductController {

    private final ProductService productService;

    //injection by constructor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    @Operation(summary = "Get All Products", description = "Return the complete list of products")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> response = new ApiResponse<>(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product by Id", description = "Return the product detail by Id in path")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        ApiResponse<Product> response = new ApiResponse<>(product);
        return ResponseEntity.ok(response);
    }
}
