package Artalia.com.example.MusicBox.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        SongResponseDto songResponseDto = new SongResponseDto(songEntity.getLink(), songEntity.getSongName(), songEntity.getArtistName());
        return songResponseDto;
    }

    public List<SongResponseDto> toSongDto(List<SongEntity> songs){
        return songs
                .stream()
                .map(this::toSongDto)
                .collect(Collectors.toList());
    }
}
