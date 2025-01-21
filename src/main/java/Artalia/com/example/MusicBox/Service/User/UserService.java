package Artalia.com.example.MusicBox.Service.User;

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
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userMapper.toUserDto(userEntity);
    }

    public UserResponseDto getByUserName(String userName){
        UserEntity userEntity = userRepository.findByUserName(userName);
        return userMapper.toUserDto(userEntity);
    }
}
