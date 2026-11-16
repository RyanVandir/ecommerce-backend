package com.ryva.ecommercebackend.application.mapper;

import com.ryva.ecommercebackend.domain.model.Product;
import com.ryva.ecommercebackend.infra.adapter.in.dto.ProductRequest;
import com.ryva.ecommercebackend.infra.adapter.in.dto.ProductResponse;
import com.ryva.ecommercebackend.infra.adapter.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toModel(ProductEntity entity);

    ProductEntity toEntity(Product model);

    List<ProductResponse> toResponse(List<Product> model);

    ProductResponse toResponse(Product model);

    default Product toModel(ProductRequest request){
        return Product.create(
                request.name(),
                request.description(),
                request.sku(),
                request.price()
        );
    }

}