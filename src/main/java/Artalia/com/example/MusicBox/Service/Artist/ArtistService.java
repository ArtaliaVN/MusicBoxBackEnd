package Artalia.com.example.MusicBox.Service.Artist;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper){
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public ArtistDto post(ArtistDto artistDto){
        artistRepository.save(artistMapper.toArtistEntity(artistDto));
        return artistDto;
    }

    public ArtistResponseDto getById(int id){
        return artistMapper.toArtistDto(artistRepository.findById(id).orElse(null));
    }

    public List<ArtistResponseDto> getByArtistName(String artistName){
        return artistMapper.toSongDto(artistRepository.findByArtistName(artistName));
    }

    public List<ArtistResponseDto> getAll(){
        return artistMapper.toSongDto(artistRepository.findAll());
    }
}
