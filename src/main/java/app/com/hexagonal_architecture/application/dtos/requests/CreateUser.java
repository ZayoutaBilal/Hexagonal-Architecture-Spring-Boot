package app.com.hexagonal_architecture.application.dtos.requests;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser {

    private String username;
    private String email;
    private String password;
    private String phone;

}
