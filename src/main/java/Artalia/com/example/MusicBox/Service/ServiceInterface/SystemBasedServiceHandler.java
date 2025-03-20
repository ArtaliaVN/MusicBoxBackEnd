package Artalia.com.example.MusicBox.Service.ServiceInterface;

import Artalia.com.example.MusicBox.Service.Role.ApplicationRole;

public interface SystemBasedServiceHandler extends ServiceHandler{
    public EntityHandler findById(int id);

    public ResponseHandler post(EntityHandler request);

    public EntityHandler findByName(ApplicationRole requestRole);
}
