package Artalia.com.example.MusicBox.Service.ServiceInterface;

import org.springframework.stereotype.Component;

@Component
public interface UserServiceHandler extends UserBasedSeviceHandler {
    public ResponseHandler post(EntityHandler entity);

    public ResponseHandler findByUsername(String name);

    public ResponseHandler findByEmail(String email);
    
    public ResponseHandler findByUsernameOrEmail(String username, String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
