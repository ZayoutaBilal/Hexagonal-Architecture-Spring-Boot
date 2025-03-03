package app.com.hexagonal_architecture.domain.services;

import app.com.hexagonal_architecture.domain.models.User;
import app.com.hexagonal_architecture.domain.models.UserDetails;

import java.util.List;

public interface UserService {

    Long save(User user);
    void updateUser(long id, UserDetails userDetails);
    void deleteUser(long id);
    User getUser(long id);
    List<User> getAllActiveUser();
}
