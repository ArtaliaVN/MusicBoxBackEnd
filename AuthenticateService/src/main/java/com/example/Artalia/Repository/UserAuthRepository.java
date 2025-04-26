package com.example.Artalia.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Artalia.Data.UserAuthEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {
    Optional<UserAuthEntity> findByUsername(String username);

    Optional<UserAuthEntity> findByEmail(String email);

    Optional<UserAuthEntity> findByEmailOrUsername(String email, String username);
}
