package br.com.javadevweek.smartdelivery;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class CreateCustomerUseCase {

    @PersistenceContext
    private EntityManager em;
    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public void execute(CustomerEntity customerEntity) {
        String url = "https://viacep.com.br/ws/" + customerEntity.getZipCode() + "/json/";

        try {
            ViaCepDTO viaCepDTO = restTemplate.getForObject(url, ViaCepDTO.class);
            customerEntity.setAddress(viaCepDTO.getLogradouro());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error to search CEP" + customerEntity.getZipCode());
        }

        String jpql = "SELECT count(c) FROM CustomerEntity c WHERE c.email = :email";
        Long count = em
            .createQuery(jpql, Long.class)
            .setParameter("email", customerEntity.getEmail())
            .getSingleResult();

        if (count > 0) {
            throw new IllegalArgumentException("Already exist account registred");
        }
        em.persist(customerEntity);
        System.out.println(customerEntity);
    }
}
