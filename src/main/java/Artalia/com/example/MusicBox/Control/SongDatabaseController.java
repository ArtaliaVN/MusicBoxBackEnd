package Artalia.com.example.MusicBox.Control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.SongDto;
import Artalia.com.example.MusicBox.Service.SongResponseDto;
import Artalia.com.example.MusicBox.Service.SongService;

@RestController
public class SongDatabaseController {
    private final SongService songService;

    public SongDatabaseController(SongService songService){
        this.songService = songService;
    }

    @PostMapping("/song/post")
    public SongResponseDto post(@RequestBody SongDto songDto){
        return songService.postSong(songDto);
    }

    @GetMapping("/song/get/id={id}")
    public SongResponseDto getById(@PathVariable("id") int id){
        return songService.getById(id);
    }

    @GetMapping("/song/get/songName={songName}")
    public List<SongResponseDto> getBySongName(@PathVariable String songName){
        return songService.getBySongName(songName);
    }

    @GetMapping("/song/get/artistName={artistName}")
    public List<SongResponseDto> getByArtistName(@PathVariable String artistName){
        return songService.getByArtistName(artistName);
    }

    @GetMapping("/song/get/all")
    public List<SongResponseDto> getAll(){
        return songService.getAll();
    }
}
