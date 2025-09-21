package br.com.javadevweek.smartdelivery.modules.customers;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.javadevweek.smartdelivery.integrations.ViaCepDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class CreateCustomerUseCase {

    private CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public void execute(CustomerEntity customerEntity) {
        String url = "https://viacep.com.br/ws/" + customerEntity.getZipCode() + "/json";

        try {
            ViaCepDTO viaCepDTO = restTemplate.getForObject(url, ViaCepDTO.class);
            customerEntity.setAddress(viaCepDTO.getLogradouro());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error to search CEP " + customerEntity.getZipCode());
        }

        customerRepository.findByEmail(customerEntity.getEmail())
            .ifPresent(
                item -> {
                    throw new IllegalArgumentException("Email já existe");
                }
            );

        this.customerRepository.save(customerEntity);
        System.out.println(customerEntity);
    }
}
