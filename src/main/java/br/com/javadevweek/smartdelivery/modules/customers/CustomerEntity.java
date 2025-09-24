package br.com.javadevweek.smartdelivery.modules.customers;
import br.com.javadevweek.smartdelivery.modules.users.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;

@Table(name = "customers")
@Entity
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;
    
    private String phone;
    private String address;
    private String zipCode;

    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    public CustomerEntity() {}

    public CustomerEntity(String name, String email, String address, String zipCode, String phone, UUID userId) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
