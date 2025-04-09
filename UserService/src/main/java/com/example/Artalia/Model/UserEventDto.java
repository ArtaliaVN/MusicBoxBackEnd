package com.example.Artalia.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventDto{
    private int id;
    private String userName;
    private String imageURL;
    private String imageID;
}
