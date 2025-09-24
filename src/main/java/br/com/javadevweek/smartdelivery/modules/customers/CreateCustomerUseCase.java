package br.com.javadevweek.smartdelivery.modules.customers;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.javadevweek.smartdelivery.integrations.ViaCepDTO;
import br.com.javadevweek.smartdelivery.modules.users.CreateUserUseCase;
import br.com.javadevweek.smartdelivery.modules.users.Role;
import jakarta.transaction.Transactional;

@Service
public class CreateCustomerUseCase {

    private CustomerRepository customerRepository;
    private CreateUserUseCase createUserUseCase;

    public CreateCustomerUseCase(CustomerRepository customerRepository, CreateUserUseCase createUserUseCase) {
        this.customerRepository = customerRepository;
        this.createUserUseCase = createUserUseCase;
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

        this.createUserUseCase.execute(customerEntity.getEmail(), customerEntity.getPassword(), Role.CUSTOMER);

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
