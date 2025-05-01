package com.example.Artalia.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.Artalia.Dto.UserEventDto;
import com.example.Artalia.Interface.UserServiceImp;
import com.example.Artalia.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Artalia.Data.CustomUserDetails;
import com.example.Artalia.Data.RoleEntity;
import com.example.Artalia.Data.UserAuthEntity;
import com.example.Artalia.Utils.JwtUtils;

@Service
public class AuthenticateService {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserServiceImp userServiceImp;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
        Authentication authentication;
        try{
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
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        UserInfoResponse response = new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), jwtToken, roles);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> register(SignUpRequest signUpRequest) {
        if(userAuthService.existsUserAuthByEmailOrUserName(signUpRequest.getEmail(), signUpRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email or Username already exists"));
        }

        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setEmail(signUpRequest.getEmail());
        userAuthEntity.setUsername(signUpRequest.getUsername()); 
        userAuthEntity.setPassword(encoder.encode(signUpRequest.getPassword()));

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
        userAuthEntity.setRoles(roles);
        userAuthService.postUserAuth(userAuthEntity);

        UserEventDto userEventDto = new UserEventDto();
        userEventDto.setEmail(signUpRequest.getEmail());
        userEventDto.setUserName(signUpRequest.getUsername());
        userEventDto.setFirstName(signUpRequest.getFirstname());
        userEventDto.setLastName(signUpRequest.getLastname());
        userServiceImp.postUser(userEventDto);

        return ResponseEntity.accepted().body("Success");//.body(authProducer.sendMessage(userEntity));
    }

    public boolean validateToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }
}
