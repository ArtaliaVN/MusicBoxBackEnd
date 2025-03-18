package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.MapperHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;

@Service
public class SongListMapper implements MapperHandler{

    @Override
    public SongListResponseDto toDto(EntityHandler entity){
        SongListEntity songList = (SongListEntity) entity;
        return new SongListResponseDto(songList.getId(), songList.getPlayListName(), songList.getSongList());
    }

    @Override
    public SongListEntity toEntity(RequestHandler request){
        SongListDto songListDto = (SongListDto) request;
        SongListEntity songListEntity = new SongListEntity();
        songListEntity.setPlayListName(songListDto.getPlayListName());
        songListEntity.setSongList(songListDto.getSongList());
        return songListEntity;
    }

    @Override
    public List<SongListResponseDto> toDto(List<? extends EntityHandler> songListEntities){
        return songListEntities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
