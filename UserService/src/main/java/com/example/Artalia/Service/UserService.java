package com.example.Artalia.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Artalia.Data.UserEntity;
import com.example.Artalia.GoogleDrive.DriveService;
import com.example.Artalia.Mapper.UserMapper;
import com.example.Artalia.Model.UserDto;
import com.example.Artalia.Model.UserResponseDto;
import com.example.Artalia.Repository.UserRepository;


@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    
    public UserResponseDto post(UserEntity userEntity){
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    public UserResponseDto post(UserDto userDto){
        UserEntity userEntity = userMapper.toEntity(userDto);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }
    
    public UserResponseDto findById(int id){
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }
    
    public UserResponseDto findByUsername(String username){
        return userMapper.toDto(userRepository.findByUserName(username));
    }
    
    public UserResponseDto findByEmail(String email){
        return userMapper.toDto(userRepository.findByEmail(email));
    }
    
    public UserResponseDto findByUsernameOrEmail(String userName, String email){
        return userMapper.toDto(userRepository.findByUserNameOrEmail(userName, email));
    }
    
    public List<UserResponseDto> getAll(){
        return userMapper.toDto(userRepository.findAll());
    }
    
    public void deleteById(int id){
        userRepository.deleteById(id);
    }
    
    public Boolean existsByUsername(String userName){
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
        return userMapper.toDto(userEntity);
    }
    
    public byte[] getImageById(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        UserResponseDto userResponseDto = (UserResponseDto) findById(id);
        return service.downloadFromFolder(userResponseDto.getImageID());
    }
    
    public UserRepository getRepo(){
        return userRepository;
    }
}
