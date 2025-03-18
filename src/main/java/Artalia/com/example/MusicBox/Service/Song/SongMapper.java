package Artalia.com.example.MusicBox.Service.Song;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.MapperHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import Artalia.com.example.MusicBox.Service.User.UserEntity;

@Service
public class SongMapper implements MapperHandler{

    @Override
    public SongEntity toEntity(RequestHandler request){
        SongDto songDto = (SongDto) request;
        SongEntity songEntity = new SongEntity();
        songEntity.setSongName(songDto.getSongName());
        songEntity.setArtistName(songDto.getArtistName());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(songDto.getUser_id());
        songEntity.setArtist(userEntity);
        return songEntity;
    }

    @Override
    public SongResponseDto toDto(EntityHandler entity){
        SongEntity songEntity = (SongEntity) entity;
        return new SongResponseDto(
            songEntity.getId(), 
            songEntity.getSongName(), 
            songEntity.getArtistName(),
            songEntity.getImageURL(),
            songEntity.getImageID(),
            songEntity.getAudioURL(),
            songEntity.getAudioID()
        );
    }

    @Override
    public List<SongResponseDto> toDto(List<? extends EntityHandler> entities){
        return entities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
