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
        userEntity.setProfileImageURL(userDto.profileImageURL());
        userEntity.setPassword(userDto.password());
        return userEntity;
    }

    public UserResponseDto toUserDto(UserEntity userEntity){
        return new UserResponseDto(userEntity.getId(), userEntity.getEmail(), userEntity.getUserName(), userEntity.getProfileImageURL(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getSongListLibrary(), userEntity.getSubscribedArtist());
    }

    public List<UserResponseDto> toUserDto(List<UserEntity> userEntities){
        return userEntities
                .stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
