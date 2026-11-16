package com.ryva.ecommercebackend.infra.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_products")
public class ProductEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean active = true;

    private LocalDateTime createdAt;
}
