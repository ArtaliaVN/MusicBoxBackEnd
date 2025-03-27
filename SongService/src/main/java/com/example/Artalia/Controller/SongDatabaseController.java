package com.example.Artalia.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/song/id={id}/item")
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

    // @GetMapping("/song/id={id}/item/image")
    // public Mono<byte[]> getImageById(@PathVariable("id") int id) throws IOException, GeneralSecurityException{
    //     return songService.getImageById(id);
    // }

    // @GetMapping("/song/id={id}/item/audio")
    // public Mono<byte[]> getAudioById(@PathVariable("id") int id) throws IOException, GeneralSecurityException{
    //     return songService.getAudioById(id);
    // }
    
    // @PatchMapping("/song/{id}/item/image/update")
    // public Mono<String> updateImageById(@PathVariable("id") int id,@RequestPart("image") FilePart image) throws IOException, GeneralSecurityException{
    //     if(image == null){
    //         return Mono.just("No file detected");
    //     }
    //     File tempFile = File.createTempFile("temp", null);
    //     image.transferTo(tempFile);
    //     songService.updateImageById(id, tempFile);
    //     return Mono.just("Successful");
    // }

    // @PatchMapping("/song/{id}/item/audio/update")
    // public Mono<String> updateAudioById(@PathVariable("id") int id,@RequestPart("audio") FilePart audio) throws IOException, GeneralSecurityException{
    //     if(audio == null){
    //         return Mono.just("No file detected");
    //     }
    //     File tempFile = File.createTempFile("temp", null);
    //     audio.transferTo(tempFile);
    //     songService.updateAudioById(id, tempFile);
    //     return Mono.just("Successful");
    // }
}
