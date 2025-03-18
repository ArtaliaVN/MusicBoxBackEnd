package Artalia.com.example.MusicBox.Service.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUserName(String userName);

    UserEntity findByEmail(String email);

    UserEntity findByUserNameOrEmail(String userName, String email);

    Boolean existsByUserName(String userName);
    
    Boolean existsByEmail(String email);
}
