package com.example.Artalia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Artalia.Data.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUserName(String userName);

    UserEntity findByEmail(String email);

    UserEntity findByUserNameOrEmail(String userName, String email);

    Boolean existsByUserName(String userName);
    
    Boolean existsByEmail(String email);
}
