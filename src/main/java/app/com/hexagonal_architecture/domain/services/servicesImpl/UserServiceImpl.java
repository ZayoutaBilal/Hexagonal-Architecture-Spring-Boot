package app.com.hexagonal_architecture.domain.services.servicesImpl;

import app.com.hexagonal_architecture.domain.enums.Role;
import app.com.hexagonal_architecture.domain.models.User;
import app.com.hexagonal_architecture.domain.models.UserDetails;
import app.com.hexagonal_architecture.domain.repositories.UserRepository;
import app.com.hexagonal_architecture.domain.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Long save(User user) {
        userRepository.findByEmailOrUsername(user.getEmail(),user.getUsername()).ifPresent( u -> {
                throw new RuntimeException("User with this email or this username is already exists !");
        });
        user.setRole(Role.ROLE_REGULAR_USER);
        return userRepository.save(user).getId();
    }

    @Override
    public void updateUser(long id, UserDetails userDetails) {
        userRepository.findById(id).ifPresentOrElse( u -> {
            u.getUserDetails().setPhone(userDetails.getPhone());
            u.getUserDetails().setFirstName(userDetails.getFirstName());
            u.getUserDetails().setLastName(userDetails.getLastName());
            u.getUserDetails().setBirthday(userDetails.getBirthday());
            userRepository.save(u);
        },() -> {
            throw new RuntimeException("The user with ID "+id+" is not found !");
                }
        );
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).ifPresent( u -> {
            u.setDeleted(Boolean.TRUE);
            userRepository.save(u);
        });
    }

    @Override
    public User getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new RuntimeException("The user with ID "+id+" is not found !");
        return user.get();
    }

    @Override
    public List<User> getAllActiveUser() {
        return userRepository.findAllByDeletedIsFalse();
    }
}
