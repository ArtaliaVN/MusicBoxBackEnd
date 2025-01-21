package Artalia.com.example.MusicBox.Service.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUserName(String userName);
}
