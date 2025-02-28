package Artalia.com.example.MusicBox.Service.Authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.User.UserEntity;
import Artalia.com.example.MusicBox.Service.User.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        return CustomUserDetails.build(userEntity);
    }
    
}
