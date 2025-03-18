package Artalia.com.example.MusicBox.Service.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.Authentication.Register.SignUpRequest;
import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.MapperHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;

@Service
public class UserMapper implements MapperHandler{
    
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserEntity toEntity(RequestHandler request){
        UserDto userDto = (UserDto) request;
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPassword(encoder.encode(userDto.getPassword()));
        return userEntity;
    }

    public UserEntity toEntity(SignUpRequest signUpRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setFirstName(signUpRequest.getFirstname());
        userEntity.setLastName(signUpRequest.getLastname());
        userEntity.setUserName(signUpRequest.getUsername());
        userEntity.setPassword(encoder.encode(signUpRequest.getPassword()));
        return userEntity;
    }

    @Override
    public UserResponseDto toDto(EntityHandler entity){
        UserEntity userEntity = (UserEntity) entity;
        return new UserResponseDto(
            userEntity.getId(), 
            userEntity.getEmail(), 
            userEntity.getUserName(), 
            userEntity.getFirstName(), 
            userEntity.getLastName(), 
            userEntity.getImageURL(), 
            userEntity.getImageID(), 
            userEntity.getSongs(), 
            userEntity.getSongListLibrary()
        );
    }
    
    @Override
    public List<UserResponseDto> toDto(List<? extends EntityHandler> userEntities){
        return userEntities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
