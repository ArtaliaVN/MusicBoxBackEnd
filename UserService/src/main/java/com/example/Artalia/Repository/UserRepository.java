package com.example.Artalia.Repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Artalia.Data.UserEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer>{
    Mono<UserEntity> findByUsername(String userName);

    Mono<UserEntity> findByEmail(String email);

    Flux<UserEntity> findByUsernameOrEmail(String userName, String email);

    Mono<Boolean> existsByUsername(String userName);
    
    Mono<Boolean> existsByEmail(String email);

}
