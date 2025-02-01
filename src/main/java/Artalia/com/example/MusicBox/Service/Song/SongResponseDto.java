package Artalia.com.example.MusicBox.Service.Song;

public record SongResponseDto(
        int id,
        String songLink,
        String coverImageURL,
        int songLength,
        String songName,
        String artistName
) {}
