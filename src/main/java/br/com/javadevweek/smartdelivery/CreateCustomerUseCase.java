package br.com.javadevweek.smartdelivery;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CreateCustomerUseCase {
    
    @PersistenceContext
    private EntityManager em;
    
    public void execute(CustomerEntity customerEntity) {
        em.persist(customerEntity);
        System.out.println(customerEntity);
    }

}
