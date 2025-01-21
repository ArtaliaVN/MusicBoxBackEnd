package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;

public record SongListResponseDto(
    int id,
    String playListName,
    List<SongEntity> songList
) {}
