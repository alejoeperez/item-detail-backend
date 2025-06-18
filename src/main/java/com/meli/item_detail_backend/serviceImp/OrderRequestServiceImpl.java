package com.meli.item_detail_backend.serviceImp;

import com.meli.item_detail_backend.dto.OrderRequest;
import com.meli.item_detail_backend.dto.Product;
import com.meli.item_detail_backend.dto.ProductVariant;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.service.OrderRequestService;
import com.meli.item_detail_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final ProductService productService;

    //injection by constructor
    public OrderRequestServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void processOrder(OrderRequest request) {

        Product product = productService.getProductById(request.getProductId());

        ProductVariant variant = product.getVariants().stream()
                .filter(v -> v.getId().equals(request.getVariantId()))
                .findFirst()
                .orElseThrow(() ->
                        new BusinessException(
                                String.format("Variant with id %s not found", request.getVariantId()),
                                HttpStatus.NOT_FOUND));

        Map<String, Integer> sizeStock = variant.getSizeStock();
        String requestedSize = request.getSize();

        if (!sizeStock.containsKey(requestedSize)) {
            throw new BusinessException(String.format("Size %s not available", requestedSize), HttpStatus.BAD_REQUEST);
        }

        int availableStock = sizeStock.get(requestedSize);
        if (availableStock < request.getQuantity()) {
            throw new BusinessException("Stock insufficient", HttpStatus.BAD_REQUEST);
        }
        // update stock
        sizeStock.put(requestedSize, availableStock - request.getQuantity());
    }
}
