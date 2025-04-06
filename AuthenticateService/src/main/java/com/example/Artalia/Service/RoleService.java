package com.example.Artalia.Service;

import org.springframework.stereotype.Service;

import com.example.Artalia.Data.RoleEntity;
import com.example.Artalia.Model.ApplicationRole;
import com.example.Artalia.Model.MessageResponse;
import com.example.Artalia.Repository.RoleRepository;

@Service
public class RoleService{
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    
    public RoleEntity findByName(ApplicationRole roleName){
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Error: Role not found"));
    }
    
    public MessageResponse post(RoleEntity role){
        roleRepository.save(role);
        return new MessageResponse("Role successfully added to the database");
    }
     
    public RoleRepository getRepo(){
        return roleRepository;
    }
    
    public void deleteById(int id){
        roleRepository.deleteById(id);
    }
    
    public RoleEntity findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
}
