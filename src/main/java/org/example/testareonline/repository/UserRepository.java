package org.example.testareonline.repository;

import org.example.testareonline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u.username FROM User u WHERE u.id = :id")
    Optional<String> findUsernameById(Integer id);
    boolean existsByUsername(String username);
}