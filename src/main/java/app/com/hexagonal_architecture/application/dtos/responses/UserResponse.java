package app.com.hexagonal_architecture.application.dtos.responses;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phone;
    private byte[] image;

}
