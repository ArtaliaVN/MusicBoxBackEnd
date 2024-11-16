package Artalia.com.example.MusicBox.Control;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.ArtistDto;
import Artalia.com.example.MusicBox.Service.ArtistEntity;
import Artalia.com.example.MusicBox.Service.ArtistRepository;

@RestController
public class ArtistDatabaseController {
    private ArtistRepository artistRepository;

    public ArtistDatabaseController(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public ArtistEntity toArtistEntity(ArtistDto artistDto){
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setArtistName(artistDto.artistName());
        artistEntity.setArtistInformation(artistDto.artistInformation());
        return artistEntity;
    }

    public ArtistDto toArtistDto(ArtistEntity artistEntity){
        ArtistDto artistDto = new ArtistDto(artistEntity.getArtistName(), artistEntity.getArtistInformation());
        return artistDto;
    }

    @PostMapping("/artist/post")
    public ArtistDto post(@RequestBody ArtistDto artistDto){
        artistRepository.save(toArtistEntity(artistDto));
        return artistDto;
    }

    @GetMapping("/artist/get/id={id}")
    public ArtistDto getById(@PathVariable int id){
        return toArtistDto(artistRepository.findById(id).orElse(null));
    }

    @GetMapping("/artist/get")
    public List<ArtistDto> getAll(){
        return artistRepository.findAll()
                    .stream()
                    .map(this::toArtistDto)
                    .collect(Collectors.toList());
    }
}
