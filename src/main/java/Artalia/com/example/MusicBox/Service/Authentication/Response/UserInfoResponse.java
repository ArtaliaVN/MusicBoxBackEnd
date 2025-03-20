package Artalia.com.example.MusicBox.Service.Authentication.Response;

import java.util.List;

import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse extends ResponseHandler{
    private int id;
    private String jwtToken;
    private String userName;
    private List<String> roles;
}
