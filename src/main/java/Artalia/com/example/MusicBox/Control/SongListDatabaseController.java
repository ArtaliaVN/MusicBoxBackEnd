package Artalia.com.example.MusicBox.Control;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.SongList.SongListDto;
import Artalia.com.example.MusicBox.Service.SongList.SongListResponseDto;
import Artalia.com.example.MusicBox.Service.SongList.SongListService;

@RestController
public class SongListDatabaseController {
    private final SongListService songListService;

    public SongListDatabaseController(SongListService songListService){
        this.songListService = songListService;
    }

    @PostMapping("/songlist")
    public SongListResponseDto post(@RequestBody SongListDto songListDto){
        return songListService.postSongList(songListDto);
    }

    @GetMapping("/songlist/id={id}/list")
    public SongListResponseDto getById(@PathVariable("id") int id){
        return songListService.getById(id);
    }

    @GetMapping("/songlist/lists")
    public List<SongListResponseDto> getAll(){
        return songListService.getAll();
    }

    @DeleteMapping("/songlist/id={id}/delete")
    public void deleteById(@PathVariable("id") int id){
        songListService.deleteById(id);
    }
}
