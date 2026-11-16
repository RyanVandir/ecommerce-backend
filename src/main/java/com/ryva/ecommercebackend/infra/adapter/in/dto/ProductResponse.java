package com.ryva.ecommercebackend.infra.adapter.in.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        String sku,
        BigDecimal price,
        Boolean active,
        LocalDateTime createdAt
) {
}
