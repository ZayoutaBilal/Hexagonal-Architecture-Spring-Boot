package app.com.hexagonal_architecture.infrastructure.repositories.entities;

import app.com.hexagonal_architecture.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private Boolean deleted = Boolean.FALSE;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserDetailsEntity userDetailsEntity;

}
