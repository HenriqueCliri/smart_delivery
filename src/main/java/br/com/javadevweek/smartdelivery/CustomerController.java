package br.com.javadevweek.smartdelivery;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/customers")
public class CustomerController {

        private CreateCustomerUseCase createCustomerUseCase;

        private CustomerController(CreateCustomerUseCase createCustomerUseCase) {
            this.createCustomerUseCase = createCustomerUseCase;
        }
    
    @PostMapping("/")
    public CustomerEntity create(@RequestBody CustomerEntity customerEntity) {
        createCustomerUseCase.execute(customerEntity);
        return customerEntity;
    }
    
}
