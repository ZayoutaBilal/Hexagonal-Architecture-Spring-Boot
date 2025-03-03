package app.com.hexagonal_architecture.domain.repositories;

import app.com.hexagonal_architecture.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByEmailOrUsername(String email, String username);

    List<User> findAllByDeletedIsFalse();
}
