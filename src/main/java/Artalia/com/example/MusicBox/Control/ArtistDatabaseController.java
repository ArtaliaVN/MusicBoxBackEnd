package Artalia.com.example.MusicBox.Control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.ArtistDto;
import Artalia.com.example.MusicBox.Service.ArtistResponseDto;
import Artalia.com.example.MusicBox.Service.ArtistService;

@RestController
public class ArtistDatabaseController {
    private final ArtistService artistService;

    public ArtistDatabaseController(ArtistService artistService){
        this.artistService = artistService;
    }

    @PostMapping("/artist/post")
    public ArtistDto post(@RequestBody ArtistDto artistDto){
        return artistService.post(artistDto);
    }

    @GetMapping("/artist/get/id={id}")
    public ArtistResponseDto getById(@PathVariable int id){
        return artistService.getById(id);
    }

    @GetMapping("/artist/get/artistName={artistName}")
    public List<ArtistResponseDto> getByArtistName(@PathVariable String artistName){
        return artistService.getByArtistName(artistName);
    }

    @GetMapping("/artist/get/all")
    public List<ArtistResponseDto> getAll(){
        return artistService.getAll();
    }
}
