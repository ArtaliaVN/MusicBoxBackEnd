package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;

public record SongListDto(
    String playListName,
    List<SongEntity> songList,
    int user_id
) {}
