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
public class SongResponseDto {
    private int id;
    private String songName;
    private String artistName;
    private String imageURL;
    private String imageID;
    private String audioURL;
    private String audioID;    

    public static SongResponseDto entityToDto(SongEntity songEntity){
        return SongResponseDto.builder()
            .id(songEntity.getId())
            .songName(songEntity.getSongname())
            .artistName(songEntity.getArtistname())
            .imageURL(songEntity.getImageurl())
            .imageID(songEntity.getImageid())
            .audioURL(songEntity.getAudiourl())
            .audioID(songEntity.getAudioid())
            .build();
    }   
}
