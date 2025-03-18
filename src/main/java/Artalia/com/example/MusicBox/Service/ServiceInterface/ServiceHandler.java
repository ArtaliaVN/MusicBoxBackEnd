package Artalia.com.example.MusicBox.Service.ServiceInterface;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ServiceHandler {
    public ResponseHandler post(RequestHandler request);

    public void deleteById(int id);

    public ResponseHandler updateImageById(int id, File file) throws IOException, GeneralSecurityException;

    public byte[] getImageById(int id) throws IOException, GeneralSecurityException;

    public ResponseHandler findById(int id);
    
    public List<? extends ResponseHandler> getAll();

    public Object getRepo();
}
