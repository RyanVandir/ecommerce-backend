package com.ryva.ecommercebackend.infra.adapter.out.persistence.service;

import com.ryva.ecommercebackend.application.mapper.ProductMapper;
import com.ryva.ecommercebackend.application.port.out.ProductPersistencePort;
import com.ryva.ecommercebackend.domain.model.Product;
import com.ryva.ecommercebackend.infra.adapter.out.persistence.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServicePersistence implements ProductPersistencePort {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Product save(Product product) {
        return mapper.toModel(
                repository.save(
                        mapper.toEntity(product)
                )
        );
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id).
                map(mapper::toModel);
    }

    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

    @Override
    public boolean existsBySkuAndIdNot(String sku, UUID id) {
        return repository.existsBySkuAndIdNot(sku, id);
    }

    @Override
    public Optional<Product> delete(UUID id) {
        return repository.findById(id)
                .map(entity -> {
                    Product product = mapper.toModel(entity);

                    repository.delete(entity);

                    return product;
                });
    }
}
