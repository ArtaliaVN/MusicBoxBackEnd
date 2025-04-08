package com.example.Artalia.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Artalia.Data.SongEntity;
import com.example.Artalia.Model.SongDto;
import com.example.Artalia.Model.SongResponseDto;

@Service
public class SongMapper{

    public SongEntity toEntity(SongDto songDto){
        SongEntity songEntity = new SongEntity();
        songEntity.setSongname(songDto.getSongName());
        songEntity.setArtistname(songDto.getArtistName());
        songEntity.setUserid(songDto.getUser_id());
        return songEntity;
    }

    
    public SongResponseDto toDto(SongEntity songEntity){
        return new SongResponseDto(
            songEntity.getId(), 
            songEntity.getSongname(), 
            songEntity.getArtistname(),
            songEntity.getImageurl(),
            songEntity.getImageid(),
            songEntity.getAudiourl(),
            songEntity.getAudioid()
        );
    }

    
    public List<SongResponseDto> toDto(List<SongEntity> entities){
        return entities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
