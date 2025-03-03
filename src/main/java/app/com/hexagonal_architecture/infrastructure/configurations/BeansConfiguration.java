package app.com.hexagonal_architecture.infrastructure.configurations;

import app.com.hexagonal_architecture.domain.repositories.UserRepository;
import app.com.hexagonal_architecture.domain.services.UserService;
import app.com.hexagonal_architecture.domain.services.servicesImpl.UserServiceImpl;
import app.com.hexagonal_architecture.infrastructure.repositories.UserEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserServiceImpl(userRepository);
    }
    @Bean
    public UserRepository userRepository(UserEntityRepository userEntityRepository) {
        return userEntityRepository;
    }
}
