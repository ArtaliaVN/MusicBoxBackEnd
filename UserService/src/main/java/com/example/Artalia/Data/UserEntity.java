package com.example.Artalia.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity{

    @Id
    private int id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String password;

    @Builder.Default
    private String imageurl = "https://drive.google.com/file/d/14YmUPBwmEkuvUee00levMbocNz-XvkWs/view?usp=drivesdk";

    @Builder.Default
    private String imageid = "14YmUPBwmEkuvUee00levMbocNz-XvkWs";
}
