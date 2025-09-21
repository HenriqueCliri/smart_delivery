package br.com.javadevweek.smartdelivery.modules.products;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findByCode(int code);
}
