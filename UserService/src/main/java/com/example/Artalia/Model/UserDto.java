package com.example.Artalia.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public String email;
    public String userName;
    public String firstName;
    public String lastName;
    public String password;
}
