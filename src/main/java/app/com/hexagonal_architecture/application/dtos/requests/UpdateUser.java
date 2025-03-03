package app.com.hexagonal_architecture.application.dtos.requests;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phone;

}
