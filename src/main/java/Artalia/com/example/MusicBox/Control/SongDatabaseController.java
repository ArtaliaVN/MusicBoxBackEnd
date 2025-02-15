package Artalia.com.example.MusicBox.Control;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/song/songName={songName}/items")
    public List<SongResponseDto> getBySongName(@PathVariable String songName){
        return songService.getBySongName(songName);
    }

    @GetMapping("/song/artistName={artistName}/items")
    public List<SongResponseDto> getByArtistName(@PathVariable String artistName){
        return songService.getByArtistName(artistName);
    }

    @GetMapping("/song/items")
    public List<SongResponseDto> getAll(){
        return songService.getAll();
    }

    @GetMapping("/song/image/id={id}/item")
    public byte[] getImageBySongId(@PathVariable int id) throws IOException, GeneralSecurityException{
        return songService.getImageBySongID(id);
    }

    @GetMapping("/song/audio/id={id}/item")
    public byte[] getSongAudioById(@PathVariable int id) throws IOException, GeneralSecurityException{
        return songService.getAudioBySongID(id);
    }

    @DeleteMapping("/song/remove/id={id}")
    public void deleteById(@PathVariable("id") int id){
        songService.deleteById(id);
    }

    @PatchMapping("/song/{id}/item/image/update")
    public String updateImageById(@PathVariable("id") int id,@RequestParam("image") MultipartFile image) throws IOException, GeneralSecurityException{
        if(image.isEmpty()){
            return "No file detected";
        }
        File tempFile = File.createTempFile("temp", null);
        image.transferTo(tempFile);
        songService.updateImageById(id, tempFile);
        return "Successful";
    }

    @PatchMapping("/song/{id}/item/audio/update")
    public String updateAudioById(@PathVariable("id") int id,@RequestParam("audio") MultipartFile audio) throws IOException, GeneralSecurityException{
        if(audio.isEmpty()){
            return "No file detected";
        }
        File tempFile = File.createTempFile("temp", null);
        audio.transferTo(tempFile);
        songService.updateAudioById(id, tempFile);
        return "Successful";
    }
}
