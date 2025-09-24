package br.com.javadevweek.smartdelivery.modules.products;

import java.util.UUID;

public record CreateProductResponse(int code, String name, UUID id) {

}
