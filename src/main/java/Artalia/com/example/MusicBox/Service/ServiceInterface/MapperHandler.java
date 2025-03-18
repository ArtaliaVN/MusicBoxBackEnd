package Artalia.com.example.MusicBox.Service.ServiceInterface;

import java.util.List;

public interface MapperHandler {
    public EntityHandler toEntity(RequestHandler request);

    public ResponseHandler toDto(EntityHandler entity);

    public List<? extends ResponseHandler> toDto(List<? extends EntityHandler> entity);
}
