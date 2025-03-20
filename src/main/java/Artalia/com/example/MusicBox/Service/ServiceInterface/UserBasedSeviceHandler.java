package Artalia.com.example.MusicBox.Service.ServiceInterface;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface UserBasedSeviceHandler extends ServiceHandler {
    public ResponseHandler findById(int id); 

    public ResponseHandler post(RequestHandler request);
    
    public ResponseHandler updateImageById(int id, File file) throws IOException, GeneralSecurityException;

    public byte[] getImageById(int id) throws IOException, GeneralSecurityException;

    public List<? extends ResponseHandler> getAll();

}
