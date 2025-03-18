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

import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import Artalia.com.example.MusicBox.Service.User.UserDto;
import Artalia.com.example.MusicBox.Service.User.UserService;

@RestController
public class UserDatabaseController {
    private final UserService userService;

    public UserDatabaseController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseHandler postUser(@RequestBody UserDto userDto){
        return userService.post(userDto);
    }

    @GetMapping("/user/{id}/account")
    public ResponseHandler getById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @GetMapping("/user/username={username}/account")
    public ResponseHandler getByUserName(@PathVariable("username") String userName){
        return userService.findByUsername(userName);
    }

    @GetMapping("/user/email={email}/account")
    public ResponseHandler getByEmail(@PathVariable("email") String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/user/username={username}_email={email}/account")
    public ResponseHandler getByUserNameOrEmail(@PathVariable("username") String username, @PathVariable("email") String email){
        return userService.findByUsernameOrEmail(username, email);
    }

    @GetMapping("/user/accounts")
    public List<? extends ResponseHandler> getAll(){
        return userService.getAll();
    }

    @GetMapping("/user/image/id={id}/account")
    public byte[] getImageByUserId(@PathVariable int id) throws IOException, GeneralSecurityException{
        return userService.getImageById(id);
    }

    @DeleteMapping("/user/id={id}/delete")
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
