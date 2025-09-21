package br.com.javadevweek.smartdelivery.modules.customers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> create(@RequestBody CustomerEntity customerEntity) {
        try {
            createCustomerUseCase.execute(customerEntity);
            return ResponseEntity.ok().build();
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }

    }
}
