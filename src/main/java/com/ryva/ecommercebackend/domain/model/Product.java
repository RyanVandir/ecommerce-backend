package com.ryva.ecommercebackend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Boolean active;
    private LocalDateTime createdAt;

    public Product(UUID id, String name, String description, String sku, BigDecimal price, Boolean active, LocalDateTime createdAt) {
        validate(price, sku, name);
        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
    }

    public static Product create(String name, String description, String sku, BigDecimal price) {
        return new Product(
                UUID.randomUUID(),
                name,
                description,
                sku,
                price,
                true,
                LocalDateTime.now()
        );
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void update(String name, String description, String sku, BigDecimal price) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Descrição inválido!");
        }
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU inválido!");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("inválido!");
        }

        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;

    }

    public void activate(){
        this.active = true;
    }
    public void deactivate(){
        this.active = false;
    }

    private void validate(BigDecimal price, String sku, String name) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que 0.");
        }
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU não pode ser nulo ou vazio.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }
    }
}
