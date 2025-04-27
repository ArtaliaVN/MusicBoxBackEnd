package com.example.Artalia.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.example.Artalia.Model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Artalia.Model.LoginRequest;
import com.example.Artalia.Model.SignUpRequest;
import com.example.Artalia.Service.AuthenticateService;

@RestController
public class AuthenticateController {
    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping("/auth/signup/user")
    public ResponseEntity<?> register(@RequestBody SignUpRequest signUpRequest) throws IOException, ExecutionException, InterruptedException {
        return authenticateService.register(signUpRequest);
    }

    @PostMapping("/auth/signin/user")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return authenticateService.authenticateUser(loginRequest);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        if(authenticateService.validateToken(token)){
            return ResponseEntity.ok().body(new MessageResponse("Token validated"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Invalid Token"));
    }
}
