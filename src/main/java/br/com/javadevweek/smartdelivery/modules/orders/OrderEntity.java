//falta criar as instancias, getters e setters

package br.com.javadevweek.smartdelivery.modules.orders;

import java.util.List;
import java.util.UUID;

import br.com.javadevweek.smartdelivery.modules.customers.CustomerEntity;
import br.com.javadevweek.smartdelivery.modules.products.ProductEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public OrderEntity() {}

    @Column(name = "customer_id")
    private UUID customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    private CustomerEntity customer;

    @ManyToMany
    @JoinTable(
        name = "orders_products",
        joinColumns = @JoinColumn(name = "orders_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.CRIADO;

    public OrderEntity(UUID customerId, CustomerEntity customer, List<ProductEntity> products, StatusOrder status) {
        this.customerId = customerId;
        this.customer = customer;
        this.products = products;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
    
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    
    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }
}
