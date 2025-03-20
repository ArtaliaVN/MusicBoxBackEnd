package Artalia.com.example.MusicBox.Service.ServiceInterface;

import org.springframework.stereotype.Component;

@Component
public interface ServiceHandler {

    public void deleteById(int id);
    
    public Object getRepo();
}
