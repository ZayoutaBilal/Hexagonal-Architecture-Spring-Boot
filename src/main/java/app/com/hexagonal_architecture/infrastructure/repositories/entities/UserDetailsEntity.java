package app.com.hexagonal_architecture.infrastructure.repositories.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_details")
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phone;
    @Lob
    @Column(columnDefinition = "BYTEA")
    @JdbcTypeCode(SqlTypes.BINARY)
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id", unique = true, nullable = false)
    private UserEntity userEntity;
}
