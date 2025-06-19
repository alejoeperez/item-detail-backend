package com.meli.item_detail_backend.serviceImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item_detail_backend.dto.Product;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private List<Product> products;

    @PostConstruct
    public void init() throws Exception {
        log.info("@PostConstruct for {}", ProductServiceImpl.class.getSimpleName());
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/products.json");
        this.products = mapper.readValue(is, new TypeReference<>() {});
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Getting complete list of products");
        return products;
    }

    @Override
    public Product getProductById(String id) {
        log.info("Getting product by id with value [{}]", id);
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessException(
                        String.format("Product with id %s not found", id), HttpStatus.NOT_FOUND));
    }
}
