package com.example.Artalia.Service;

import com.example.Artalia.Data.UserAuthEntity;
import com.example.Artalia.Repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    public void postUserAuth(UserAuthEntity userAuthEntity) {
        userAuthRepository.save(userAuthEntity);
    }

    public void deleteUserAuth(UserAuthEntity userAuthEntity) {
        userAuthRepository.delete(userAuthEntity);
    }

    public UserAuthEntity getUserAuthByEmail(String email) {
        return userAuthRepository.findByEmail(email).orElse(null);
    }

    public UserAuthEntity getUserAuthByUsername(String username) {
        return userAuthRepository.findByUsername(username).orElse(null);
    }

    public Boolean existsUserAuthByEmailOrUserName(String email, String username) {
        return userAuthRepository.findByEmailOrUsername(email, username).isPresent();
    }
}
