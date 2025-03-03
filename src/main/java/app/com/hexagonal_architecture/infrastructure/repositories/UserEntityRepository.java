package app.com.hexagonal_architecture.infrastructure.repositories;

import app.com.hexagonal_architecture.domain.models.User;
import app.com.hexagonal_architecture.domain.models.UserDetails;
import app.com.hexagonal_architecture.domain.repositories.UserRepository;
import app.com.hexagonal_architecture.infrastructure.repositories.entities.UserDetailsEntity;
import app.com.hexagonal_architecture.infrastructure.repositories.entities.UserEntity;
import app.com.hexagonal_architecture.infrastructure.repositories.jpa.UserDetailsJpaRepository;
import app.com.hexagonal_architecture.infrastructure.repositories.jpa.UserJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserEntityRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDetailsJpaRepository userDetailsJpaRepository;
    private final ModelMapper modelMapper;

    public UserEntityRepository(UserJpaRepository userJpaRepository, UserDetailsJpaRepository userDetailsJpaRepository, ModelMapper modelMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDetailsJpaRepository = userDetailsJpaRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<User> findById(long id) {
        return userJpaRepository.findById(id)
                .map(ue -> modelMapper.map(ue,User.class));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        if(!Objects.isNull(user.getUserDetails())) {
            UserDetailsEntity userDetailsEntity = modelMapper.map(user.getUserDetails(), UserDetailsEntity.class);
            userDetailsEntity.setUserEntity(userEntity);
            userEntity.setUserDetailsEntity(userDetailsEntity);
        }
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return modelMapper.map(savedEntity,User.class);
    }

    @Override
    public Optional<User> findByEmailOrUsername(String email, String username) {
        Optional<UserEntity> userEntity = userJpaRepository.findByEmailOrUsername(email, username);
        return userEntity.map(entity -> modelMapper.map(entity, User.class));
    }

    @Override
    public List<User> findAllByDeletedIsFalse() {
        return userJpaRepository.findAllByDeletedIsFalse().stream()
                .map(u ->  modelMapper.map(u, User.class))
                .collect(Collectors.toList());
    }
}

