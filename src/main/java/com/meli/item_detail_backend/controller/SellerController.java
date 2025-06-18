package com.meli.item_detail_backend.controller;

import com.meli.item_detail_backend.common.ApiResponse;
import com.meli.item_detail_backend.dto.Seller;
import com.meli.item_detail_backend.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "*")
@Tag(name = "Sellers", description = "Operations related with sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Seller by Id", description = "Return the seller detail by Id in path")
    public ResponseEntity<ApiResponse<Seller>> getSellerById(@PathVariable String id) {
        Seller seller = sellerService.getSellerById(id);
        ApiResponse<Seller> response = new ApiResponse<>(seller);
        return ResponseEntity.ok(response);
    }
}
