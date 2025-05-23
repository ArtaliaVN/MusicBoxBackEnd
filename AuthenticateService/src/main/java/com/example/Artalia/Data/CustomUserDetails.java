package com.example.Artalia.Data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Service
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int id;

    private String userName;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String userName, String email, String password, List<GrantedAuthority> authorities){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(UserAuthEntity userAuthEntity){
        List<GrantedAuthority> authorities = userAuthEntity.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
            .collect(Collectors.toList());
        
        return new CustomUserDetails(
            userAuthEntity.getUserId(),
            userAuthEntity.getUsername(),
            userAuthEntity.getEmail(),
            userAuthEntity.getPassword(),
            authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
    
    @Override
    public boolean isEnabled(){
        return true;
    }
}
