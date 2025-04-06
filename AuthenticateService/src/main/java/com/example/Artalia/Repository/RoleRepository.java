package com.example.Artalia.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Artalia.Data.RoleEntity;
import com.example.Artalia.Model.ApplicationRole;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(ApplicationRole roleName);
}
