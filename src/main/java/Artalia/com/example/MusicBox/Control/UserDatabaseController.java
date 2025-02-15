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

import Artalia.com.example.MusicBox.Service.User.UserDto;
import Artalia.com.example.MusicBox.Service.User.UserResponseDto;
import Artalia.com.example.MusicBox.Service.User.UserService;

@RestController
public class UserDatabaseController {
    private final UserService userService;

    public UserDatabaseController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserResponseDto postUser(@RequestBody UserDto userDto){
        return userService.postUser(userDto);
    }

    @GetMapping("/user/{id}/account")
    public UserResponseDto getById(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @GetMapping("/user/username={username}/account")
    public UserResponseDto getByUserName(@PathVariable("username") String userName){
        return userService.getByUserName(userName);
    }

    @GetMapping("/user/email={email}/account")
    public UserResponseDto getByEmail(@PathVariable("email") String email){
        return userService.getByEmail(email);
    }

    @GetMapping("/user/accounts")
    public List<UserResponseDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/user/image/id={id}/account")
    public byte[] getImageByUserId(@PathVariable int id) throws IOException, GeneralSecurityException{
        return userService.getImageByUserID(id);
    }

    @DeleteMapping("/user/delete/id={id}")
    public void deleteById(@PathVariable("id") int id){
        userService.deleteById(id);
    }

    @PatchMapping("/user/id={id}/account/image/update")
    public String updateImageById(@PathVariable("id") int id,@RequestParam("image") MultipartFile image) throws IOException, GeneralSecurityException{
        if(image.isEmpty()){
            return "No file detected";
        }
        File tempFile = File.createTempFile("temp", null);
        image.transferTo(tempFile);
        userService.updateImageById(id, tempFile);
        return "Successful";
    }
}
