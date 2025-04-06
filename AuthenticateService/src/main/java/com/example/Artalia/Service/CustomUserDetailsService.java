package com.example.Artalia.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Artalia.Data.CustomUserDetails;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        return CustomUserDetails.build(userEntity);
    }
    
}
