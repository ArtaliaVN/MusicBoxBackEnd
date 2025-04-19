package com.example.Artalia.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongEventDto {
    private int id;
    private String userName;
    private String imageURL;
    private String imageID;
    private String audioURL;
    private String audioID;
}
