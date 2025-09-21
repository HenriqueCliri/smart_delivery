package br.com.javadevweek.smartdelivery.modules.customers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID>{
    Optional<CustomerEntity> findByEmail(String email);
}
