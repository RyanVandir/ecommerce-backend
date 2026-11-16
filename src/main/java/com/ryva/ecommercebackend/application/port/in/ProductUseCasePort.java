package com.ryva.ecommercebackend.application.port.in;

import com.ryva.ecommercebackend.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductUseCasePort {
    Product register(Product product);

    List<Product> findAll();

    Product findById(UUID id);

    Product update(UUID id, Product product);

    void changeStatus(UUID id, boolean active);

    void delete(UUID id);
}
