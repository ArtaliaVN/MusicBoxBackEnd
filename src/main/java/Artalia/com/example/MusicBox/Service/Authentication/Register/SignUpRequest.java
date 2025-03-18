package Artalia.com.example.MusicBox.Service.Authentication.Register;

import java.util.Set;

import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SignUpRequest extends RequestHandler {
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
