package Artalia.com.example.MusicBox.Service.Authentication.Response;

import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class MessageResponse extends ResponseHandler{
    private String message;
}
