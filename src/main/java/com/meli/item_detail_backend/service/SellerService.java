package com.meli.item_detail_backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item_detail_backend.dto.Seller;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface SellerService {

    Seller getSellerById(String id);

}
