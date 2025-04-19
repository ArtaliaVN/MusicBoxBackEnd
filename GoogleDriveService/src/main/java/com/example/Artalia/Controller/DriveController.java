package com.example.Artalia.Controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.Artalia.Dto.SongEventDto;
import com.example.Artalia.Dto.UserEventDto;
import com.example.Artalia.Event.Producer;
import com.example.Artalia.Interface.SongServiceImp;
import com.example.Artalia.Interface.UserServiceImp;
import com.example.Artalia.Service.DriveService;

import reactor.core.publisher.Mono;

@RestController
public class DriveController {
    @Autowired
    private DriveService driveService;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired 
    private SongServiceImp songServiceImp;

    @Autowired
    private Producer producer;

    @PostMapping("media/user/{id}/image/update")
    public Mono<ResponseEntity<?>> userImagePatch(@RequestPart("image") MultipartFile imageFile, @PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        UserEventDto userEventDto = userServiceImp.getById(userid);
        String imageID = driveService.uploadImageToFolder("user","image" ,imageFile, userEventDto.getUserName());
        userEventDto.setImageID(imageID);
        userEventDto.setImageURL(driveService.getWebViewLink(imageID));
        producer.sendUserEventMessage(userEventDto);
        return Mono.just(ResponseEntity.ok(userEventDto));
    }

    @GetMapping("media/user/{id}/image/fetch")
    public Mono<byte[]> userImageFetch(@PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        UserEventDto userEventDto = userServiceImp.getById(userid);
        return Mono.just(driveService.downloadFromFolder(userEventDto.getImageID()));
    }   

    @PostMapping("media/song/{id}/image/update")
    public Mono<ResponseEntity<?>> songImagePatch(@RequestPart("image") MultipartFile imageFile, @PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        SongEventDto songEventDto = songServiceImp.getById(userid);
        String imageID = driveService.uploadImageToFolder("user","image" ,imageFile, songEventDto.getUserName());
        songEventDto.setImageID(imageID);
        songEventDto.setImageURL(driveService.getWebViewLink(imageID));
        producer.sendSongEventMessage(songEventDto);
        return Mono.just(ResponseEntity.ok(songEventDto));
    }

    @GetMapping("media/song/{id}/image/fetch")
    public Mono<byte[]> songImageFetch(@PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        SongEventDto songEventDto = songServiceImp.getById(userid);
        return Mono.just(driveService.downloadFromFolder(songEventDto.getImageID()));
    }   

    @PostMapping("media/song/{id}/audio/update")
    public Mono<ResponseEntity<?>> songAudioPatch(@RequestPart("audio") MultipartFile audioFile, @PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        SongEventDto songEventDto = songServiceImp.getById(userid);
        String audioID = driveService.uploadAudioToFolder("user","audio" ,audioFile, songEventDto.getUserName());
        songEventDto.setAudioID(audioID);
        songEventDto.setAudioURL(driveService.getWebViewLink(audioID));
        producer.sendSongEventMessage(songEventDto);
        return Mono.just(ResponseEntity.ok(songEventDto));
    }

    @GetMapping("media/song/{id}/audio/fetch")
    public Mono<byte[]> songAudioFetch(@PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        SongEventDto songEventDto = songServiceImp.getById(userid);
        return Mono.just(driveService.downloadFromFolder(songEventDto.getAudioID()));
    }   
}
