package com.ryva.ecommercebackend.infra.adapter.out.persistence.repository;

import com.ryva.ecommercebackend.infra.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    // Retorna true se já existir um produto com esse sku
    boolean existsBySku(String sku);

    // Exclui o produto sendo atualizado
    boolean existsBySkuAndIdNot(String sku, UUID id);
}