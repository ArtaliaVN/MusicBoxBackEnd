package Artalia.com.example.MusicBox.Control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.Authentication.AuthenticationService;
import Artalia.com.example.MusicBox.Service.Authentication.Login.LoginRequest;
import Artalia.com.example.MusicBox.Service.Authentication.Register.SignUpRequest;
import jakarta.validation.Valid;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/user/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        return authenticationService.authenticateUser(loginRequest);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authenticationService.register(signUpRequest);
    }
}
