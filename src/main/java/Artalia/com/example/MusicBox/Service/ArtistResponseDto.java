package Artalia.com.example.MusicBox.Service;

import java.util.List;

public record ArtistResponseDto (
        String artistName,
        String artistInformation,
        String email,
        List<SongEntity> songs
) {
}