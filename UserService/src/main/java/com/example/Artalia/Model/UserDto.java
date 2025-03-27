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
public class UserDto {
    public String email;
    public String userName;
    public String firstName;
    public String lastName;
    public String password;

    public static UserEntity dtoToEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstname(userDto.getFirstName());
        userEntity.setLastname(userDto.getLastName());
        userEntity.setUsername(userDto.getUserName());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }
}
