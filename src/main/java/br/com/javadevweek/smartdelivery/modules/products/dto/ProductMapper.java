package br.com.javadevweek.smartdelivery.modules.products.dto;

import br.com.javadevweek.smartdelivery.modules.products.ProductEntity;

public class ProductMapper {
    public static ProductEntity requestToEntity(CreateProductRequest createProductRequest) {
        return new ProductEntity(
            createProductRequest.getCode(),
            createProductRequest.getName(),
            createProductRequest.getDescription(),
            createProductRequest.getPrice()
        );
    }
}
