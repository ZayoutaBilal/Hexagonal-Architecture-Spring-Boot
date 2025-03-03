package app.com.hexagonal_architecture.infrastructure.repositories.jpa;

import app.com.hexagonal_architecture.infrastructure.repositories.entities.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsJpaRepository extends JpaRepository<UserDetailsEntity, Long> {
}
