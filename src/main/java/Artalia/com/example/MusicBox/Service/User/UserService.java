package Artalia.com.example.MusicBox.Service.User;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.GoogleDrive.DriveService;
import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.MapperHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.UserServiceHandler;

@Service
public class UserService implements UserServiceHandler{
    private final MapperHandler userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseHandler post(EntityHandler entity){
        userRepository.save((UserEntity) entity);
        return userMapper.toDto((UserEntity) entity);
    }

    @Override
    public ResponseHandler post(RequestHandler request) {
        UserEntity userEntity = (UserEntity) userMapper.toEntity((UserDto) request);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    public ResponseHandler findById(int id){
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseHandler findByUsername(String username){
        return userMapper.toDto(userRepository.findByUserName(username));
    }

    @Override
    public ResponseHandler findByEmail(String email){
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public ResponseHandler findByUsernameOrEmail(String userName, String email){
        return userMapper.toDto(userRepository.findByUserNameOrEmail(userName, email));
    }

    @Override
    public List<? extends ResponseHandler> getAll(){
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    @Override
    public Boolean existsByUsername(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseHandler updateImageById(int id, File image) throws IOException, GeneralSecurityException{
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String imageID = service.uploadImageToFolder("user", image, userEntity.getUserName());
        String imageURL = service.getWebViewLink(imageID);
        userEntity.setImageID(imageID);
        userEntity.setImageURL(imageURL);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    public byte[] getImageById(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        UserResponseDto userResponseDto = (UserResponseDto) findById(id);
        return service.downloadFromFolder(userResponseDto.getImageID());
    }

    @Override
    public UserRepository getRepo(){
        return userRepository;
    }
}
