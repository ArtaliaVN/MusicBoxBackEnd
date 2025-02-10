package Artalia.com.example.MusicBox.Service.Song;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.User.UserEntity;

@Service
public class SongMapper {
    public SongEntity toSongEntity(SongDto songDto){
        SongEntity songEntity = new SongEntity();
        songEntity.setSongName(songDto.songName());
        songEntity.setArtistName(songDto.artistName());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(songDto.user_id());
        songEntity.setArtist(userEntity);
        return songEntity;
    }

    public SongResponseDto toSongDto(SongEntity songEntity){
        return new SongResponseDto(
            songEntity.getId(), 
            songEntity.getSongName(), 
            songEntity.getArtistName(),
            songEntity.getImageURL(),
            songEntity.getImage(),
            songEntity.getAudioURL(),
            songEntity.getAudio()
        );
    }

    public List<SongResponseDto> toSongDto(List<SongEntity> songs){
        return songs
                .stream()
                .map(this::toSongDto)
                .collect(Collectors.toList());
    }
}
