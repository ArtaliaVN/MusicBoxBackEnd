package Artalia.com.example.MusicBox.Service.Authentication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.Authentication.Login.LoginRequest;
import Artalia.com.example.MusicBox.Service.Authentication.Register.SignUpRequest;
import Artalia.com.example.MusicBox.Service.Authentication.Response.MessageResponse;
import Artalia.com.example.MusicBox.Service.Authentication.Response.UserInfoResponse;
import Artalia.com.example.MusicBox.Service.User.UserEntity;
import Artalia.com.example.MusicBox.Service.User.UserMapper;
import Artalia.com.example.MusicBox.Service.User.UserService;

import org.springframework.http.HttpStatus;

import Artalia.com.example.MusicBox.Service.Role.RoleEntity;

@Service
public class AuthenticationService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthenticationService(JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserService userService, UserMapper userMapper){
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
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

    public ResponseEntity<?> register(SignUpRequest signUpRequest){
        if(userService.existsByUserName(signUpRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username existed"));
        }

        if(userService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email has already been use"));
        }

        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Please enter matching password"));
        }

        UserEntity userEntity = userMapper.toUserEntity(signUpRequest);
        Set<String> strRoles = signUpRequest.getRoles();
        Set<RoleEntity> roles = new HashSet<>();

        if(strRoles.isEmpty()){
            
        }  

        userEntity.setRoles(roles);
        return ResponseEntity.ok(userService.postUser(userEntity));
    }
}
