package br.com.javadevweek.smartdelivery.modules.products;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.javadevweek.smartdelivery.modules.products.dto.ProductMapper;

@Service
public class ProductService {
    private ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ListProductResponse> findAll() {
        var products = this.productsRepository.findAll();
        return ProductMapper.toResponse(products);
    }
}
