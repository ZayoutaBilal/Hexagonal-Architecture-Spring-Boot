package app.com.hexagonal_architecture.application.RestApi;


import app.com.hexagonal_architecture.application.dtos.requests.CreateUser;
import app.com.hexagonal_architecture.application.dtos.requests.UpdateUser;
import app.com.hexagonal_architecture.application.dtos.responses.UserResponse;
import app.com.hexagonal_architecture.domain.models.User;
import app.com.hexagonal_architecture.domain.models.UserDetails;
import app.com.hexagonal_architecture.domain.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="user")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> saveNewUser(@RequestBody CreateUser createUser){
        try {
            long id = userService.save(mapper.map(createUser,User.class));
            return ResponseEntity.status(HttpStatus.OK).body("The new user has been saved successfully with ID="+id);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody UpdateUser updateUser){
        try {
            userService.updateUser(userId,mapper.map(updateUser, UserDetails.class));
            return ResponseEntity.status(HttpStatus.OK).body("The user has been updated successfully");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllActiveUser(){
        List<UserResponse> list = userService.getAllActiveUser()
                .stream().map(e -> mapper.map(e,UserResponse.class))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUser(@PathVariable long userId){
        try {
            UserResponse userResponse = mapper.map(userService.getUser(userId),UserResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("The user has been deleted successfully");
    }

}
