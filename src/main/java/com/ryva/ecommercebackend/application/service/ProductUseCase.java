package com.ryva.ecommercebackend.application.service;

import com.ryva.ecommercebackend.application.port.in.ProductUseCasePort;
import com.ryva.ecommercebackend.application.port.out.ProductPersistencePort;
import com.ryva.ecommercebackend.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductUseCase implements ProductUseCasePort {

    private final ProductPersistencePort persistence;

    @Override
    public Product register(Product product) {

        Product newProduct = Product.create(
                product.getName(),
                product.getSku(),
                product.getDescription(),
                product.getPrice()
        );

        if (persistence.existsBySku(newProduct.getSku())) {
            throw new RuntimeException("SKU já existente" + newProduct.getSku());
        }

        return persistence.save(newProduct);
    }

    @Override
    public List<Product> findAll() {

        List<Product> products = persistence.findAll();

        if (products.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado!");
        }

        return products;
    }

    @Override
    public Product findById(UUID id) {

        return persistence.findById(id)
                .orElseThrow(() -> new RuntimeException("produto não encontrado!"));
    }

    @Override
    public Product update(UUID id, Product product) {

        Product existProduct = persistence.findById(id)
                .orElseThrow(() -> new RuntimeException("produto não encontrado!"));

        if (persistence.existsBySkuAndIdNot(product.getSku(), id)) {
            throw new RuntimeException("SKU já pertence a outro produto!" + product.getSku());
        }

        existProduct.update(
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getPrice()
        );

        return persistence.save(existProduct);
    }

    @Override
    public void changeStatus(UUID id, boolean active) {

        Product product = persistence.findById(id)
                .orElseThrow(() -> new RuntimeException("produto não encontrado!"));

        if (persistence.existsBySkuAndIdNot(product.getSku(), id)) {
            throw new RuntimeException("SKU já pertence a outro produto!" + product.getSku());
        }

        if (active) {
            product.activate();
        } else {
            product.deactivate();
        }

        persistence.save(product);
    }

    @Override
    public void delete(UUID id) {
        persistence.delete(id)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
