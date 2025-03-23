package com.example.Artalia.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Service;
import com.example.Artalia.GoogleDrive.DriveService;
import com.example.Artalia.Model.SongDto;
import com.example.Artalia.Model.SongResponseDto;
import com.example.Artalia.Repository.SongRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public Mono<SongResponseDto> post(SongDto songDto){
        return Mono.just(songDto)
                .map(SongDto::dtoToEntity)
                .flatMap(songEntity -> songRepository.save(songEntity))
                .map(SongResponseDto::entityToDto)
                .doOnError(throwable -> new Throwable(throwable.getMessage()))
                .doOnSuccess(songResponseDto -> new String("Success"));
    }
    
    public Mono<SongResponseDto> findById(int id){
        return songRepository.findById(id)
                .map(SongResponseDto::entityToDto)
                .switchIfEmpty(Mono.error(new Throwable("Song item with this id was not found")));
    }
    
    public Flux<SongResponseDto> findByItemName(String songName){
        return songRepository.findBySongname(songName)
                .map(SongResponseDto::entityToDto)
                .switchIfEmpty(Mono.error(new Throwable("Song item with this song name was not found")));
    }

    public Flux<SongResponseDto> findByUsername(String userName){
        return songRepository.findByArtistname(userName)
                .map(SongResponseDto::entityToDto)
                .switchIfEmpty(Mono.error(new Throwable("Song item with this artist name was not found")));
    }

    public Flux<SongResponseDto> findByUserId(int userId){
        return songRepository.findByUserid(userId)
                .map(SongResponseDto::entityToDto)
                .switchIfEmpty(Mono.error(new Throwable("Song item with this artist id was not found")));
    }

    public Flux<SongResponseDto> getAll(){
        return songRepository.findAll()
                .map(SongResponseDto::entityToDto)
                .switchIfEmpty(Mono.error(new Throwable("No item was found")));
    }
    
    public void deleteById(int id){
        songRepository.deleteById(id);
    }

    public Mono<SongResponseDto> updateImageById(int id, File image) {
        return songRepository.findById(id)
                .switchIfEmpty(Mono.error(new Throwable("Song item with this id was not found")))
                .flatMap(songEntity ->{
                    DriveService service = new DriveService();
                    String imageID;
                    try {
                        imageID = service.uploadImageToFolder("song", image, songEntity.getSongname());
                        String imageURL = service.getWebViewLink(imageID);
                        songEntity.setImageid(imageID);
                        songEntity.setImageurl(imageURL);
                    } catch (IOException | GeneralSecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return songRepository.save(songEntity);
                })
                .map(SongResponseDto::entityToDto);
    }
    
    public Mono<SongResponseDto> updateAudioById(int id, File audio) {
        return songRepository.findById(id)
                .switchIfEmpty(Mono.error(new Throwable("Song item with this id was not found")))
                .flatMap(songEntity ->{
                    DriveService service = new DriveService();
                    String audioID;
                    try {
                        audioID = service.uploadAudioToFolder("song", audio, songEntity.getSongname());
                        String audioURL = service.getWebViewLink(audioID);
                        songEntity.setAudioid(audioID);
                        songEntity.setAudiourl(audioURL);
                    } catch (IOException | GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                    return songRepository.save(songEntity);
                })
                .map(SongResponseDto::entityToDto);
    }
    
    public SongRepository getRepo() {
        return songRepository;
    }
}
