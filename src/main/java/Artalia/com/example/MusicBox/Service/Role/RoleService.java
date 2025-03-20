package Artalia.com.example.MusicBox.Service.Role;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.Authentication.Response.MessageResponse;
import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.SystemBasedServiceHandler;

@Service
public class RoleService implements SystemBasedServiceHandler{
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity findByName(ApplicationRole roleName){
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public ResponseHandler post(EntityHandler request){
        RoleEntity role = (RoleEntity) request;
        roleRepository.save(role);
        return new MessageResponse("Role successfully added to the database");
    }

    @Override 
    public RoleRepository getRepo(){
        return roleRepository;
    }

    @Override
    public void deleteById(int id){
        roleRepository.deleteById(id);
    }

    @Override
    public RoleEntity findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
}
