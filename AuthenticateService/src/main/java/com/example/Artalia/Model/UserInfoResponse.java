package com.example.Artalia.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse{
    private int id;
    private String userName;
    private String jwtToken;
    private List<String> roles;
}
