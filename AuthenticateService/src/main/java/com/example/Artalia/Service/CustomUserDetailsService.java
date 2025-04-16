package com.example.Artalia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Artalia.Data.CustomUserDetails;
import com.example.Artalia.Data.UserAuthEntity;
import com.example.Artalia.Repository.UserAuthRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthEntity userAuthEntity = userAuthRepository.findByUsername(username).orElseThrow();
        return CustomUserDetails.build(userAuthEntity);
    }
    
}
