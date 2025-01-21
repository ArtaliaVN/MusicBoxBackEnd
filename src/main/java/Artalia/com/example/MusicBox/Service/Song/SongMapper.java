package Artalia.com.example.MusicBox.Service.Song;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.Artist.ArtistEntity;

@Service
public class SongMapper {
    public SongEntity toSongEntity(SongDto songDto){
        SongEntity songEntity = new SongEntity();
        songEntity.setLink(songDto.link());
        songEntity.setSongName(songDto.songName());
        songEntity.setArtistName(songDto.artistName());
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setArtistID(songDto.artist_id());
        songEntity.setArtist(artistEntity);
        return songEntity;
    }

    public SongResponseDto toSongDto(SongEntity songEntity){
        return new SongResponseDto(songEntity.getSongID(), songEntity.getLink(), songEntity.getSongName(), songEntity.getArtistName());
    }

    public List<SongResponseDto> toSongDto(List<SongEntity> songs){
        return songs
                .stream()
                .map(this::toSongDto)
                .collect(Collectors.toList());
    }
}
