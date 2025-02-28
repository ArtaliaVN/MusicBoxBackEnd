package Artalia.com.example.MusicBox.Service.Authentication.Register;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private Set<String> roles;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
