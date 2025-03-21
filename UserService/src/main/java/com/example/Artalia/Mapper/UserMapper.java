package com.example.Artalia.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.Artalia.Data.UserEntity;
import com.example.Artalia.Model.SignUpRequest;
import com.example.Artalia.Model.UserDto;
import com.example.Artalia.Model.UserResponseDto;

@Service
public class UserMapper{
    
    public UserEntity toEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

    public UserEntity toEntity(SignUpRequest signUpRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setFirstName(signUpRequest.getFirstname());
        userEntity.setLastName(signUpRequest.getLastname());
        userEntity.setUserName(signUpRequest.getUsername());
        userEntity.setPassword(signUpRequest.getPassword());
        return userEntity;
    }

    
    public UserResponseDto toDto(UserEntity userEntity){
        return new UserResponseDto(
            userEntity.getId(), 
            userEntity.getEmail(), 
            userEntity.getUserName(), 
            userEntity.getFirstName(), 
            userEntity.getLastName(), 
            userEntity.getImageURL(), 
            userEntity.getImageID()
        );
    }
    
    public List<UserResponseDto> toDto(List<UserEntity> userEntities){
        return userEntities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
