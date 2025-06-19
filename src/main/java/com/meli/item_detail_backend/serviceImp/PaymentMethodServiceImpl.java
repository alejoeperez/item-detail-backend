package com.meli.item_detail_backend.serviceImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item_detail_backend.dto.PaymentMethod;
import com.meli.item_detail_backend.service.PaymentMethodService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private static final Logger log = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);
    
    private List<PaymentMethod> paymentMethods;

    @PostConstruct
    public void init() throws Exception {
        log.info("@PostConstruct for {}", PaymentMethodServiceImpl.class.getSimpleName());
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/payment-methods.json");
        this.paymentMethods = mapper.readValue(is, new TypeReference<>() {});
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        log.info("Getting complete list of Payment Methods");
        return paymentMethods;
    }
}
