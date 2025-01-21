package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;

public record UserResponseDto(
    String email,
    String userName,
    String firstName,
    String lastName,
    List<SongListEntity> songListLibrary
) {}
