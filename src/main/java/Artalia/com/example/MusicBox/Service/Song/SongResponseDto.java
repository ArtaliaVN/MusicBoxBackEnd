package Artalia.com.example.MusicBox.Service.Song;

public record SongResponseDto(
        int id,
        String songLink,
        int songLength,
        String songName,
        String artistName
) {}
