package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(String id);

}
