package com.example.Artalia.Model;

import com.example.Artalia.Data.SongEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongDto {
    private String songName;
    private String artistName;
    private int user_id;

    public static SongEntity dtoToEntity(SongDto songDto){
        SongEntity songEntity = new SongEntity();
        songEntity.setSongname(songDto.getSongName());
        songEntity.setArtistname(songDto.getArtistName());
        songEntity.setUserid(songDto.getUser_id());
        return songEntity;
    }
}
