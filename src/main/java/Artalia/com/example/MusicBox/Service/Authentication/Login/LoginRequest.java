package Artalia.com.example.MusicBox.Service.Authentication.Login;

import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest extends RequestHandler {
    private String username;
    private String password;
}
