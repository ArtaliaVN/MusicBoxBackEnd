package Artalia.com.example.MusicBox.Service.Artist;

import java.util.List;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;

public record ArtistResponseDto (
        String artistName,
        String artistInformation,
        String email,
        List<SongEntity> songs
) {}