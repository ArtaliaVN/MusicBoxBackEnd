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

import com.example.Artalia.Dto.UserResponseDto;
import com.example.Artalia.Interface.UserServiceImp;
import com.example.Artalia.Service.DriveService;

import reactor.core.publisher.Mono;

@RestController
public class DriveController {
    @Autowired
    private DriveService driveService;

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("media/user/{id}/image/update")
    public Mono<ResponseEntity<?>> userImagePatch(@RequestPart("image") MultipartFile imageFile, @PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        UserResponseDto userResponseDto = userServiceImp.getById(userid);
        String imageID = driveService.uploadImageToFolder("user","image" ,imageFile, userResponseDto.getUserName());
        userResponseDto.setImageID(imageID);
        userResponseDto.setImageURL(driveService.getWebViewLink(imageID));
        return Mono.just(ResponseEntity.ok(userResponseDto));
    }

    @GetMapping("media/user/{id}/image/fetch")
    public Mono<byte[]> userImageFetch(@PathVariable("id") int userid) throws IOException, GeneralSecurityException{
        UserResponseDto userResponseDto = userServiceImp.getById(userid);
        return Mono.just(driveService.downloadFromFolder(userResponseDto.getImageID()));
    }   
}
