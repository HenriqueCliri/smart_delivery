package br.com.javadevweek.smartdelivery.modules.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    public UserRepository userRepository;
    private PasswordEncoder encoder;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserEntity execute(String username, String password, Role role) {
        this.userRepository
        .findByUsername(username)
        .ifPresent(user -> { 
            throw new IllegalArgumentException("Usuário já está cadastrado"); 
        });

        UserEntity user = new UserEntity(
            username,
            encoder.encode(password), 
            role
        );
        return this.userRepository.save(user);
    }
}