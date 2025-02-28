package Artalia.com.example.MusicBox.Service.User;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.GoogleDrive.DriveService;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserResponseDto postUser(UserDto userDto){
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    public UserResponseDto postUser(UserEntity userEntity){
        userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    public UserResponseDto getById(int id){
        return userMapper.toUserDto(userRepository.findById(id).orElse(null));
    }

    public UserResponseDto getByUserName(String userName){
        return userMapper.toUserDto(userRepository.findByUserName(userName));
    }

    public UserResponseDto getByEmail(String email){
        return userMapper.toUserDto(userRepository.findByEmail(email));
    }

    public UserResponseDto getByUserNameOrEmail(String userName, String email){
        return userMapper.toUserDto(userRepository.findByUserNameOrEmail(userName, email));
    }

    public List<UserResponseDto> getAll(){
        return userMapper.toUserDto(userRepository.findAll());
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public Boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public UserResponseDto updateImageById(int id, File image) throws IOException, GeneralSecurityException{
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String imageID = service.uploadImageToFolder("user", image, userEntity.getUserName());
        String imageURL = service.getWebViewLink(imageID);
        userEntity.setImageID(imageID);
        userEntity.setImageURL(imageURL);
        userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    public byte[] getImageByUserID(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        UserResponseDto userResponseDto = getById(id);
        return service.downloadFromFolder(userResponseDto.imageID());
    }

    public UserRepository getRepo(){
        return userRepository;
    }
}
