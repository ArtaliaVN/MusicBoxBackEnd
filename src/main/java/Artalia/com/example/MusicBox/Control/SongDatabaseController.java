package Artalia.com.example.MusicBox.Control;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.Song.SongDto;
import Artalia.com.example.MusicBox.Service.Song.SongResponseDto;
import Artalia.com.example.MusicBox.Service.Song.SongService;

@RestController
public class SongDatabaseController {
    private final SongService songService;

    public SongDatabaseController(SongService songService){
        this.songService = songService;
    }

    @PostMapping("/song")
    public SongResponseDto post(@RequestBody SongDto songDto){
        return songService.postSong(songDto);
    }

    @GetMapping("/song/id={id}/account")
    public SongResponseDto getById(@PathVariable("id") int id){
        return songService.getById(id);
    }

    @GetMapping("/song/songName={songName}/account")
    public List<SongResponseDto> getBySongName(@PathVariable String songName){
        return songService.getBySongName(songName);
    }

    @GetMapping("/song/artistName={artistName}/account")
    public List<SongResponseDto> getByArtistName(@PathVariable String artistName){
        return songService.getByArtistName(artistName);
    }

    @GetMapping("/song/accounts")
    public List<SongResponseDto> getAll(){
        return songService.getAll();
    }

    @DeleteMapping("/song/remove/id={id}")
    public void deleteById(@PathVariable("id") int id){
        songService.deleteById(id);
    }
}
