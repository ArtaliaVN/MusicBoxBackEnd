package com.example.Artalia.Service;

import org.springframework.stereotype.Service;
import com.example.Artalia.Model.UserDto;
import com.example.Artalia.Model.UserResponseDto;
import com.example.Artalia.Repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Mono<UserResponseDto> post(UserDto userDto){
        return Mono.just(userDto)
                .map(UserDto::dtoToEntity)
                .flatMap(userEntity -> userRepository.save(userEntity))
                .map(UserResponseDto::entityToDto)
                .doOnError(throwable -> new Throwable(throwable.getMessage()))
                .doOnSuccess(songResponseDto -> new String("Success"));
    }
    
    public Mono<UserResponseDto> findById(int id){
        return userRepository.findById(id)
            .map(UserResponseDto::entityToDto)
            .switchIfEmpty(Mono.error(new Throwable("No item with email found")));
    }
    
    public Mono<UserResponseDto> findByUsername(String username){
        return userRepository.findByUsername(username)
            .map(UserResponseDto::entityToDto)
            .switchIfEmpty(Mono.error(new Throwable("No item with email found")));
    }
    
    public Mono<UserResponseDto> findByEmail(String email){
        return userRepository.findByEmail(email)
            .map(UserResponseDto::entityToDto)
            .switchIfEmpty(Mono.error(new Throwable("No item with email found")));
    }
    
    public Mono<UserResponseDto> findByUsernameOrEmail(String userName, String email){
        return userRepository.findByUsernameOrEmail(userName, email)
            .map(UserResponseDto::entityToDto)
            .switchIfEmpty(Mono.error(new Throwable("No item with username or email found")));
    }
    
    public Flux<UserResponseDto> getAll(){
        return userRepository.findAll()
            .map(UserResponseDto::entityToDto)
            .switchIfEmpty(Mono.error(new Throwable("No item found")));
    }
    
    public void deleteById(int id){
        userRepository.deleteById(id);
    }
    
    public Mono<Boolean> existsByUsername(String userName){
        return userRepository.existsByUsername(userName);
    }
    
    public Mono<Boolean> existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    // public UserResponseDto updateImageById(int id, File image) throws IOException, GeneralSecurityException{
    //     UserEntity userEntity = userRepository.findById(id).orElse(null);
    //     DriveService service = new DriveService();
    //     String imageID = service.uploadImageToFolder("user", image, userEntity.getUserName());
    //     String imageURL = service.getWebViewLink(imageID);
    //     userEntity.setImageID(imageID);
    //     userEntity.setImageURL(imageURL);
    //     userRepository.save(userEntity);
    //     return userMapper.toDto(userEntity);
    // }
    
    // public byte[] getImageById(int id) throws IOException, GeneralSecurityException{
    //     DriveService service = new DriveService();
    //     UserResponseDto userResponseDto = (UserResponseDto) findById(id);
    //     return service.downloadFromFolder(userResponseDto.getImageID());
    // }
    
    public UserRepository getRepo(){
        return userRepository;
    }
}
