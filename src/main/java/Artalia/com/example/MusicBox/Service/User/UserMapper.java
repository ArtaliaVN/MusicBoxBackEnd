package Artalia.com.example.MusicBox.Service.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserEntity toUserEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.email());
        userEntity.setFirstName(userDto.firstName());
        userEntity.setLastName(userDto.lastName());
        userEntity.setUserName(userDto.userName());
        userEntity.setPassword(userDto.password());
        userEntity.setImage(userDto.imageID());
        userEntity.setImageURL(userDto.imageURL());
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
            userEntity.getImage(), 
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
