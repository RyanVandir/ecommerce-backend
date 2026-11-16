package com.ryva.ecommercebackend.application.port.out;

import com.ryva.ecommercebackend.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPersistencePort {

    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(UUID id);
    boolean existsBySku(String sku);
    boolean existsBySkuAndIdNot(String sku, UUID id);
    Optional<Product> delete(UUID id);
}
