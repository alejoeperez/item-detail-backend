package com.meli.item_detail_backend.serviceImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item_detail_backend.dto.Seller;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.service.SellerService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private List<Seller> sellers;

    private static final Logger log = LoggerFactory.getLogger(SellerServiceImpl.class);

    @PostConstruct
    public void init() throws IOException {
        log.info("@PostConstruct for {}", SellerServiceImpl.class.getSimpleName());
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/sellers.json");
        this.sellers = mapper.readValue(is, new TypeReference<>() {});
    }

    @Override
    public Seller getSellerById(String id) {
        log.info("Getting seller by id with value [{}]", id);
        return sellers.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessException(String.format("Seller with id %s not found", id), HttpStatus.NOT_FOUND));
    }
}
