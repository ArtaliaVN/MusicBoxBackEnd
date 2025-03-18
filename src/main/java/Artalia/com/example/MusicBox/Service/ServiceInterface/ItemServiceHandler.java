package Artalia.com.example.MusicBox.Service.ServiceInterface;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ItemServiceHandler extends ServiceHandler {
    public List<? extends ResponseHandler> findByUsername(String userName);

    public List<? extends ResponseHandler> findByItemName(String itemname);

    public ResponseHandler updateAudioById(int id, File file) throws IOException, GeneralSecurityException;

    public byte[] getAudioById(int id) throws IOException, GeneralSecurityException;
}
