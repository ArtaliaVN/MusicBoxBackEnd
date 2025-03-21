package com.example.Artalia.Data;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UserEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique= true)
    @NotBlank
    @Email
    private String email;

    @Column(unique= true)
    @NotBlank
    private String userName;

    @Column 
    @NotBlank
    private String firstName;

    @Column 
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    private String password;

    @Column 
    private String imageURL = "https://drive.google.com/file/d/14YmUPBwmEkuvUee00levMbocNz-XvkWs/view?usp=drivesdk";

    @Column 
    private String imageID = "14YmUPBwmEkuvUee00levMbocNz-XvkWs";

    @Column
    private Set<String> roles;
}
