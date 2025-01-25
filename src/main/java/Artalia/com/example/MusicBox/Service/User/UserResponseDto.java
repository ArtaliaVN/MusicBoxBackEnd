package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import Artalia.com.example.MusicBox.Service.Artist.ArtistEntity;
import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;

public record UserResponseDto(
    int id,
    String email,
    String userName,
    String firstName,
    String lastName,
    List<SongListEntity> songListLibrary,
    List<ArtistEntity> subscribedArtist
) {}
