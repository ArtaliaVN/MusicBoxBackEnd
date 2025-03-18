package Artalia.com.example.MusicBox.Service.User;

import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends RequestHandler{
    public String email;
    public String userName;
    public String firstName;
    public String lastName;
    public String password;
}
