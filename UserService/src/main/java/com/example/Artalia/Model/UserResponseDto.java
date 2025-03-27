package com.example.Artalia.Model;
import com.example.Artalia.Data.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto{
    private int id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String imageURL;
    private String imageID;

    public static UserResponseDto entityToDto(UserEntity userEntity){
        return UserResponseDto.builder()
            .id(userEntity.getId())
            .email(userEntity.getEmail())
            .userName(userEntity.getUsername())
            .firstName(userEntity.getFirstname())
            .lastName(userEntity.getLastname())
            .imageURL(userEntity.getImageurl())
            .imageID(userEntity.getImageid())
            .build();
    }
}
