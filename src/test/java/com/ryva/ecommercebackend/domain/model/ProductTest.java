package com.ryva.ecommercebackend.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    // Arrange
    private Product createProduct() {
        return Product.create(
                "Notebook",
                "Notebook Intel i7",
                "NOTE-001",
                new BigDecimal("3500.00")
        );
    }

    @Test
    void shouldCreate() {
        //Act
        Product product = createProduct();
        //Assert
        assertAll(
                () -> assertNotNull(product),
                () -> assertNotNull(product.getId()),
                () -> assertTrue(product.isActive()),
                () -> assertNotNull(product.getCreatedAt()),
                () -> assertEquals("Notebook", product.getName()),
                () -> assertEquals("NOTE-001", product.getSku()),
                () -> assertEquals(new BigDecimal("3500.00"), product.getPrice())
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        //Arrange
        String name = null;
        BigDecimal price = new BigDecimal("200.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> Product.create(
                        name,
                        "8gb ram i7",
                        "7186-iwj",
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        //Arrange
        String name = "";
        BigDecimal price = new BigDecimal("200.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> Product.create(
                        name,
                        "8gb ram i7",
                        "7186-iwj",
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenSkuIsNull() {
        //Arrange
        String sku = null;
        BigDecimal price = new BigDecimal("200.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> Product.create(
                        "Notebook",
                        "8gb ram i7",
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenSkuIsEmpty() {
        //Arrange
        String sku = "";
        BigDecimal price = new BigDecimal("200.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> Product.create(
                        "Notebook",
                        "8gb ram i7",
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        //Arrange
        BigDecimal price = null;
        //Assert + Act
        assertThrows(NullPointerException.class,
                () -> Product.create(
                        "Notebook",
                        "8gb ram i7",
                        "NOTE-90",
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        //Arrange
        BigDecimal price = new BigDecimal("-100");

        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> Product.create(
                        "Notebook",
                        "8gb ram i7",
                        "NOTE-90",
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        //Arrange
        BigDecimal price = BigDecimal.ZERO;

        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> Product.create(
                        "Notebook",
                        "8gb ram i7",
                        "NOTE-90",
                        price
                )
        );
    }

    @Test
    void shouldUpdate() {
        // Arrange
        Product product = createProduct();
        UUID id = product.getId();
        LocalDateTime cretedAt = product.getCreatedAt();
        //Act
        product.update(
                "Notebook Gamer",
                "16GB RAM Intel i7",
                "NOTE-002",
                new BigDecimal("4500.00")
        );
        //Assert
        assertAll(
                () -> assertEquals("Notebook Gamer", product.getName()),
                () -> assertEquals("16GB RAM Intel i7", product.getDescription()),
                () -> assertEquals("NOTE-002", product.getSku()),
                () -> assertEquals(new BigDecimal("4500.00"), product.getPrice()),
                () -> assertEquals(id, product.getId()),
                () -> assertEquals(cretedAt, product.getCreatedAt())
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdateNameIsEmpty() {
        //Arrange
        Product product = createProduct();
        String name = "";
        String description = "sjdfde";
        String sku = "NOTE-88";
        BigDecimal price = new BigDecimal("200.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> product.update(
                        name,
                        description,
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdateDescriptionIsEmpty() {
        //Arrange
        Product product = createProduct();
        String name = "Notebook 18";
        String description = "";
        String sku = "NOTE-84";
        BigDecimal price = new BigDecimal("250.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> product.update(
                        name,
                        description,
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdateSkuIsEmpty() {
        //Arrange
        Product product = createProduct();
        String name = "Notebook 18";
        String description = "notebook uebd";
        String sku = "";
        BigDecimal price = new BigDecimal("200.00");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> product.update(
                        name,
                        description,
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdatePriceIsZero() {
        //Arrange
        Product product = createProduct();
        String name = "Notebook 18";
        String description = "Notebook 182u3yuh";
        String sku = "NOTE-888";
        BigDecimal price = BigDecimal.ZERO;
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> product.update(
                        name,
                        description,
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdatePriceIsNegative() {
        //Arrange
        Product product = createProduct();
        String name = "Notebook 18";
        String description = "Notebook 182u3yuh";
        String sku = "NOTE-888";
        BigDecimal price = new BigDecimal("-1000");
        //Assert + Act
        assertThrows(IllegalArgumentException.class,
                () -> product.update(
                        name,
                        description,
                        sku,
                        price
                )
        );
    }

    @Test
    void shouldDeactivate() {
        //Arrange
        Product product = createProduct();
        //Act
        product.deactivate();
        //Assert
        assertFalse(product.isActive());
    }

    @Test
    void shouldActivate() {
        //Arrange
        Product product = createProduct();
        product.deactivate();
        //Act
        product.activate();
        //Assert
        assertTrue(product.isActive());
    }

    @Test
    void shouldRemainActiveWhenActivatedAgain() {
        //Arrange
        Product product = createProduct();
        //Act
        product.activate();
        //Assert
        assertTrue(product.isActive());
    }

    @Test
    void shouldRemainInactiveWhenDeactivatedAgain() {
        //Arrange
        Product product = createProduct();
        product.deactivate();
        //Act
        product.deactivate();
        //Assert
        assertFalse(product.isActive());
    }
}