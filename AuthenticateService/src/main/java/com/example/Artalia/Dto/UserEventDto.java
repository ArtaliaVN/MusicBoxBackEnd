package com.example.Artalia.Dto;

import com.example.Artalia.Data.UserAuthEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventDto {
    public String email;
    public String userName;
    public String firstName;
    public String lastName;
}
