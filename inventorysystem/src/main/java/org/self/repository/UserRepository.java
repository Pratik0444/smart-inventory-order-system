package org.self.repository;

import java.util.Optional;

import org.self.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User>findByEmail(String email);
   boolean existsByEmail(String email);
}
