package Artalia.com.example.MusicBox.Service.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.Authentication.Register.SignUpRequest;

@Service
public class UserMapper {
    
    @Autowired
    private PasswordEncoder encoder;

    public UserEntity toUserEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.email());
        userEntity.setFirstName(userDto.firstName());
        userEntity.setLastName(userDto.lastName());
        userEntity.setUserName(userDto.userName());
        userEntity.setPassword(encoder.encode(userDto.password()));
        return userEntity;
    }

    public UserEntity toUserEntity(SignUpRequest signUpRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setFirstName(signUpRequest.getFirstname());
        userEntity.setLastName(signUpRequest.getLastname());
        userEntity.setUserName(signUpRequest.getUsername());
        userEntity.setPassword(encoder.encode(signUpRequest.getPassword()));
        return userEntity;
    }

    public UserResponseDto toUserDto(UserEntity userEntity){
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
    
    public List<UserResponseDto> toUserDto(List<UserEntity> userEntities){
        return userEntities
                .stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
