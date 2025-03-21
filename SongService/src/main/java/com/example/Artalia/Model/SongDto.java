package com.example.Artalia.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private String songName;
    private String artistName;
    private int user_id;
}
