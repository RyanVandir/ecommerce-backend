package com.ryva.ecommercebackend.infra.adapter.in.controller;

import com.ryva.ecommercebackend.application.mapper.ProductMapper;
import com.ryva.ecommercebackend.application.port.in.ProductUseCasePort;
import com.ryva.ecommercebackend.domain.model.Product;
import com.ryva.ecommercebackend.infra.adapter.in.dto.ChangeProductStatusRequest;
import com.ryva.ecommercebackend.infra.adapter.in.dto.ProductRequest;
import com.ryva.ecommercebackend.infra.adapter.in.dto.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductUseCasePort service;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(service.register(mapper.toModel(request))));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getId(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> put(@PathVariable UUID id, @RequestBody ProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResponse(service.update(id, mapper.toModel(request))));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable UUID id, @RequestBody ChangeProductStatusRequest request) {
        service.changeStatus(id, request.active());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
