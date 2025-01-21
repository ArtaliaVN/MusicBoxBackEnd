package Artalia.com.example.MusicBox.Control;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.User.UserDto;
import Artalia.com.example.MusicBox.Service.User.UserResponseDto;
import Artalia.com.example.MusicBox.Service.User.UserService;

@RestController
public class UserDatabaseController {
    private final UserService userService;

    public UserDatabaseController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/post")
    public UserResponseDto postUser(@RequestBody UserDto userDto){
        return userService.postUser(userDto);
    }

    @GetMapping("/user/get/id={id}")
    public UserResponseDto getById(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @GetMapping("/user/get/username={username}")
    public UserResponseDto getByUserName(@PathVariable("username") String userName){
        return userService.getByUserName(userName);
    }

    @GetMapping("/user/get/all")
    public List<UserResponseDto> getAll(){
        return userService.getAll();
    }

    @DeleteMapping("/user/delete/id={id}")
    public void deleteById(@PathVariable("id") int id){
        userService.deleteById(id);
    }
}
