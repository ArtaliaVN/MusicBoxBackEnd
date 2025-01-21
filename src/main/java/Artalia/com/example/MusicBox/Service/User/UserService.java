package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public UserResponseDto getById(int id){
        return userMapper.toUserDto(userRepository.findById(id).orElse(null));
    }

    public UserResponseDto getByUserName(String userName){
        return userMapper.toUserDto(userRepository.findByUserName(userName));
    }

    public List<UserResponseDto> getAll(){
        return userMapper.toUserDto(userRepository.findAll());
    }
}
