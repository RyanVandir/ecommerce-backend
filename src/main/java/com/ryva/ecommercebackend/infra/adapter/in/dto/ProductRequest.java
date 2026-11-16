package com.ryva.ecommercebackend.infra.adapter.in.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        String sku,
        BigDecimal price
) {
}
