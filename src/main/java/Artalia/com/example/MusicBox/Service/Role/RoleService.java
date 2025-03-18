package Artalia.com.example.MusicBox.Service.Role;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.Authentication.Response.MessageResponse;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleEntity findByRoleName(ApplicationRole roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public MessageResponse addRole(RoleEntity role){
        roleRepository.save(role);
        return new MessageResponse("Role successfully added to the database");
    }
}
