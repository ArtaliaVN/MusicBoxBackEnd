package com.example.Artalia.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
