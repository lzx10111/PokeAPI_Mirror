package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUsuarioByUsername(String username);

    @Query(value = "SELECT * FROM usuarios WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);
}
