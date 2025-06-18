package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.PaymentMethod;
import com.meli.item_detail_backend.serviceImp.PaymentMethodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodServiceTest {

    private PaymentMethodServiceImpl paymentMethodService;

    @BeforeEach
    void setUp() throws Exception{
        PaymentMethod method = new PaymentMethod();
        method.setId("pm-1");

        paymentMethodService = new PaymentMethodServiceImpl() {{

                var field = PaymentMethodServiceImpl.class.getDeclaredField("paymentMethods");
                field.setAccessible(true);
                field.set(this, List.of(method));

        }};
    }

    @Test
    void getAllPaymentMethods_OK() {
        List<PaymentMethod> methods = paymentMethodService.getAllPaymentMethods();
        Assertions.assertNotNull(methods);
        Assertions.assertEquals(1, methods.size());
        Assertions.assertEquals("pm-1", methods.get(0).getId());
    }
}
