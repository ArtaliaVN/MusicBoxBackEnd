package com.example.Artalia.Model;

import lombok.Data;

@Data
public class UserEvent {
    private int id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String imageURL;
    private String imageID;
}
