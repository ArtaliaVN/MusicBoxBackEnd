package Artalia.com.example.MusicBox.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ArtistMapper {
    public ArtistEntity toArtistEntity(ArtistDto artistDto){
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setArtistName(artistDto.artistName());
        artistEntity.setArtistInformation(artistDto.artistInformation());
        artistEntity.setEmail(artistDto.email());
        return artistEntity;
    }

    public ArtistResponseDto toArtistDto(ArtistEntity artistEntity){
        ArtistResponseDto artistResponseDto = new ArtistResponseDto(artistEntity.getArtistName(), artistEntity.getArtistInformation(), artistEntity.getEmail(), artistEntity.getSongs());
        return artistResponseDto;
    }

    public List<ArtistResponseDto> toSongDto(List<ArtistEntity> artists){
        return artists
                .stream()
                .map(this::toArtistDto)
                .collect(Collectors.toList());
    }
}
