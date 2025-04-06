package com.example.Artalia.Controller;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Artalia.Data.UserEntity;
import com.example.Artalia.Kafka.UserProducer;
import com.example.Artalia.Model.UserDto;
import com.example.Artalia.Model.UserResponseDto;
import com.example.Artalia.Service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class UserDatabaseController {
    private final UserService userService;

    private final UserProducer userProducer;

    public UserDatabaseController(UserService userService, UserProducer userProducer){
        this.userProducer = userProducer;
        this.userService = userService;
    }

    @PostMapping("/user")
    public Mono<UserResponseDto> postUser(@RequestBody UserDto userDto){
        return userService.post(userDto);
    }

    @GetMapping("/user/{id}/account")
    public Mono<UserResponseDto> getById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @GetMapping("/user/username={username}/account")
    public Mono<UserResponseDto> getByUserName(@PathVariable("username") String userName){
        return userService.findByUsername(userName);
    }

    @GetMapping("/user/email={email}/account")
    public Mono<UserResponseDto> getByEmail(@PathVariable("email") String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/user/username={username}_email={email}/accounts")
    public Flux<UserResponseDto> getByUserNameOrEmail(@PathVariable("username") String username, @PathVariable("email") String email){
        return userService.findByUsernameOrEmail(username, email);
    }

    @GetMapping("/user/accounts")
    public Flux<UserResponseDto> getAll(){
        return userService.getAll();
    }

    // @GetMapping("/user/image/id={id}/account")
    // public Mono<byte[]> getImageByUserId(@PathVariable int id) throws IOException, GeneralSecurityException{
    //     return userService.getImageById(id);
    // }

    @DeleteMapping("/user/id={id}/delete")
    public void deleteById(@PathVariable("id") int id){
        UserEntity userEvent = userService.getRepo().findById(id).block(Duration.of(1000, ChronoUnit.MILLIS));
        userService.deleteById(id);
    }

    // @PatchMapping("/user/id={id}/account/image/update")
    // public Mono<String> updateImageById(@PathVariable("id") int id,@RequestPart("image") MultipartFile image) throws IOException, GeneralSecurityException{
    //     if(image.isEmpty()){
    //         return Mono.just("No file detected");
    //     }
    //     File tempFile = File.createTempFile("temp", null);
    //     image.transferTo(tempFile);
    //     return userService.updateImageById(id, tempFile);
    // }
}
