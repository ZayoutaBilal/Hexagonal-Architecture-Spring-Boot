package app.com.hexagonal_architecture.infrastructure.configurations;

import app.com.hexagonal_architecture.application.dtos.requests.CreateUser;
import app.com.hexagonal_architecture.application.dtos.responses.UserResponse;
import app.com.hexagonal_architecture.domain.models.User;
import app.com.hexagonal_architecture.domain.models.UserDetails;
import app.com.hexagonal_architecture.infrastructure.repositories.entities.UserDetailsEntity;
import app.com.hexagonal_architecture.infrastructure.repositories.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<CreateUser, User>() {
            @Override
            protected void configure() {
                map().getUserDetails().setPhone(source.getPhone());
            }
        });

        modelMapper.addMappings(new PropertyMap<User, UserResponse>() {
            @Override
            protected void configure() {
                map().setBirthday(source.getUserDetails().getBirthday());
                map().setFirstName(source.getUserDetails().getFirstName());
                map().setLastName(source.getUserDetails().getLastName());
                map().setPhone(source.getUserDetails().getPhone());
                map().setImage(Objects.requireNonNullElse(source.getUserDetails().getImage(),new byte[0]));
            }
        });

        modelMapper.typeMap(UserEntity.class, User.class)
                .addMappings(mapper -> mapper.map(UserEntity::getUserDetailsEntity, User::setUserDetails));

        return modelMapper;
    }
}
