package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;

public record UserResponseDto(
    int id,
    String email,
    String userName,
    String firstName,
    String lastName,
    String imageURL,
    String imageID,
    List<SongEntity> songs,
    List<SongListEntity> songListLibrary
) {}
