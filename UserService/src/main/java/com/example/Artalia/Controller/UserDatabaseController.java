package com.example.Artalia.Controller;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Artalia.Model.UserEventDto;
import com.example.Artalia.Model.UserDto;
import com.example.Artalia.Model.UserResponseDto;
import com.example.Artalia.Service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class UserDatabaseController {
    private final UserService userService;

    public UserDatabaseController(UserService userService){
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

    @DeleteMapping("/user/id={id}/delete")
    public void deleteById(@PathVariable("id") int id){
        userService.deleteById(id);
    }

    @PatchMapping("/user/id={id}/update/media/image")
    public Mono<UserResponseDto> updateImageByUserId(@PathVariable("id") int id, @RequestBody UserEventDto userevenDto) throws InterruptedException, ExecutionException{
        return userService.updateImageInfo(userevenDto); 
    }
}
