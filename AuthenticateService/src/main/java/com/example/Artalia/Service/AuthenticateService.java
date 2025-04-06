package com.example.Artalia.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Artalia.Data.CustomUserDetails;
import com.example.Artalia.Data.RoleEntity;
import com.example.Artalia.Kafka.AuthProducer;
import com.example.Artalia.Model.ApplicationRole;
import com.example.Artalia.Model.LoginRequest;
import com.example.Artalia.Model.MessageResponse;
import com.example.Artalia.Model.SignUpRequest;
import com.example.Artalia.Model.UserEntity;
import com.example.Artalia.Model.UserInfoResponse;
import com.example.Artalia.Utils.JwtUtils;

@Service
public class AuthenticateService {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthProducer authProducer;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exeption) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        UserInfoResponse response = new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), jwtToken, roles);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> register(SignUpRequest signUpRequest) throws IOException{
        URL obj = new URL("http://localhost:9212/user/username=${signUpRequest.username}_email=${signUpRequest.email}");
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode == 200)
            return ResponseEntity.badRequest().body(new MessageResponse("Username or email has been taken"));
        
        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword()))
            return ResponseEntity.badRequest().body(new MessageResponse("Please enter matching passwords"));

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setUsername(signUpRequest.getUsername()); 
        userEntity.setFirstname(signUpRequest.getFirstname());
        userEntity.setLastname(signUpRequest.getLastname());
        userEntity.setPassword(encoder.encode(signUpRequest.getPassword()));
           
        Set<String> strRoles = signUpRequest.getRoles();
        Set<RoleEntity> roles = new HashSet<>();
        if(strRoles == null){
            RoleEntity roleEntity = roleService.findByName(ApplicationRole.ROLE_USER);
            roles.add(roleEntity);
        }
        else{
            strRoles.forEach(role -> {
                switch(role){
                    case "admin" -> {
                        RoleEntity adminRole = roleService.findByName(ApplicationRole.ROLE_ADMIN);
                        roles.add(adminRole);
                    }

                    default -> {
                        RoleEntity userRole = roleService.findByName(ApplicationRole.ROLE_USER);
                        roles.add(userRole);
                    }
                }
            });
        }
            
        return ResponseEntity.accepted().body(authProducer.sendMessage(userEntity));
    }


}
