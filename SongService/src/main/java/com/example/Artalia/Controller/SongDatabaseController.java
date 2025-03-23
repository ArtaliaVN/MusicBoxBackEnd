package com.example.Artalia.Controller;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.Artalia.Model.SongDto;
import com.example.Artalia.Model.SongResponseDto;
import com.example.Artalia.Service.SongService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class SongDatabaseController {
    private final SongService songService;

    public SongDatabaseController(SongService songService){
        this.songService = songService;
    }

    @PostMapping("/song")
    public Mono<SongResponseDto> post(@RequestBody SongDto songDto){
        return songService.post(songDto);
    }

    @GetMapping("/song/id={id}/account")
    public Mono<SongResponseDto> findById(@PathVariable("id") int id){
        return songService.findById(id);
    }

    @GetMapping("/song/songName={songName}/items")
    public Flux<SongResponseDto> findByName(@PathVariable("songName") String songName){
        return songService.findByItemName(songName);
    }

    @GetMapping("/song/artistName={artistName}/items")
    public Flux<SongResponseDto> findByUsername(@PathVariable("artistName") String artistName){
        return songService.findByUsername(artistName);
    }

    @GetMapping("/song/items")
    public Flux<SongResponseDto> getAll(){
        return songService.getAll();
    }

    @DeleteMapping("/song/id={id}/delete")
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
