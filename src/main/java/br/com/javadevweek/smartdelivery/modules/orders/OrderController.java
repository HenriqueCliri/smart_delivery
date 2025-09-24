package br.com.javadevweek.smartdelivery.modules.orders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javadevweek.smartdelivery.modules.orders.dto.CreateOrderRequest;
import br.com.javadevweek.smartdelivery.modules.orders.dto.CreateOrderResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private CreateOrderUseCase createOrderUseCase;
    
    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping("/")
    public CreateOrderResponse create(@RequestBody CreateOrderRequest createOrderRequest){
        return this.createOrderUseCase.execute(createOrderRequest);
    }
    
}
