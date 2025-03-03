package app.com.hexagonal_architecture.infrastructure.repositories.jpa;


import app.com.hexagonal_architecture.infrastructure.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailOrUsername(String email, String username);
    List<UserEntity> findAllByDeletedIsFalse();

}