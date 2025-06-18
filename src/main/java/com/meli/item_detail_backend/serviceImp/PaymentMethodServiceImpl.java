package com.meli.item_detail_backend.serviceImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item_detail_backend.dto.PaymentMethod;
import com.meli.item_detail_backend.service.PaymentMethodService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private List<PaymentMethod> paymentMethods;

    @PostConstruct
    public void init() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/payment-methods.json");
        this.paymentMethods = mapper.readValue(is, new TypeReference<>() {});
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethods;
    }
}
