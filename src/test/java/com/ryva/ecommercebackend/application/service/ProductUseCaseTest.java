package com.ryva.ecommercebackend.application.service;

import com.ryva.ecommercebackend.application.port.out.ProductPersistencePort;
import com.ryva.ecommercebackend.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private ProductPersistencePort persistence;

    @InjectMocks
    private ProductUseCase useCase;

    @Test
    void shouldRegisterProduct() {
        Product product = new Product(
                UUID.randomUUID(),
                "Notebook",
                "8gb ram i7",
                "7186-iwj",
                new BigDecimal("200.00"),
                true,
                LocalDateTime.parse("2026-06-06T14:30:00"));

        when(persistence.save(any(Product.class)))
                .thenReturn(product);

        Product result = useCase.register(product);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(product.getName(), result.getName()),
                () -> assertEquals(product.getPrice(), result.getPrice()),
                () -> assertEquals(product.getSku(), result.getSku()),
                () -> assertTrue(result.isActivate())
        );

        verify(persistence).save(product);

    }

    @Test
    void sholudFindAllProducts() {

    }

    @Test
    void shouldFindById() {
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldDeactivate() {
    }
}