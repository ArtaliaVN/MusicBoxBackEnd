package com.example.Artalia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto{
    private int id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String imageURL;
    private String imageID;
}
