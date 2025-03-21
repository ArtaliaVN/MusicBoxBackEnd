package com.example.Artalia.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongResponseDto {
    private int id;
    private String songName;
    private String artistName;
    private String imageURL;
    private String imageID;
    private String audioURL;
    private String audioID;    
}
