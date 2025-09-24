package br.com.javadevweek.smartdelivery.modules.products;

import org.springframework.stereotype.Service;

import br.com.javadevweek.smartdelivery.modules.products.dto.CreateProductRequest;
import br.com.javadevweek.smartdelivery.modules.products.dto.ProductMapper;

@Service
public class CreateProductsUseCase {
    private ProductsRepository productsRepository;

    public CreateProductsUseCase(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository; 
    }

    public CreateProductResponse execute(CreateProductRequest createProductRequest){
        this.productsRepository.findByCode(createProductRequest.getCode())
            .ifPresent(product -> {
                throw new IllegalArgumentException("Produto já está cadastrado");
            });
        
        
        ProductEntity productEntity = ProductMapper.requestToEntity(createProductRequest);
        this.productsRepository.save(productEntity);
        return ProductMapper.entityToResponse(productEntity);
    };
}