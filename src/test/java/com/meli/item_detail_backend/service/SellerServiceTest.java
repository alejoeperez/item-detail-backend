package com.meli.item_detail_backend.service;

import com.meli.item_detail_backend.dto.Seller;
import com.meli.item_detail_backend.exception.BusinessException;
import com.meli.item_detail_backend.serviceImp.SellerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {

    private SellerServiceImpl sellerService;

    @BeforeEach
    void setUp() throws Exception{
        Seller seller = new Seller();
        seller.setId("seller-1");
        seller.setName("JAPAMALABUDISTA");
        seller.setTotalProducts(26);
        seller.setSales(104);
        seller.setMercadoLider(true);
        seller.setLeaderComment("¡Uno de los mejores del sitio!");
        seller.setProgress(90);
        seller.setBadges(List.of("Ventas concretadas", "Buena atención", "Entrega a tiempo"));

        // Simulamos la carga de datos sin leer el archivo
        sellerService = new SellerServiceImpl() {{

                var field = SellerServiceImpl.class.getDeclaredField("sellers");
                field.setAccessible(true);
                field.set(this, List.of(seller));

        }};
    }

    @Test
    void getSellerById_OK() {
        Seller result = sellerService.getSellerById("seller-1");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("JAPAMALABUDISTA", result.getName());
    }

    @Test
    void getSellerById_BusinessException() {
        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> {
            sellerService.getSellerById("invalid-id");
        });

        Assertions.assertEquals("Seller with id invalid-id not found", ex.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
