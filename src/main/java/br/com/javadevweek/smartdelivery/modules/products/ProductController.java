package br.com.javadevweek.smartdelivery.modules.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javadevweek.smartdelivery.modules.products.dto.CreateProductRequest;

@RequestMapping("/products")
@RestController
public class ProductController {
    
    CreateProductsUseCase createProductsUseCase;

    public ProductController(CreateProductsUseCase createProductsUseCase    ) {
        this.createProductsUseCase = createProductsUseCase;
    }

    public ResponseEntity<?> create(@RequestBody CreateProductRequest createProductRequest){
        try {
            var productCreated = createProductsUseCase.execute(createProductRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
        } catch(IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
        }
    }
}
