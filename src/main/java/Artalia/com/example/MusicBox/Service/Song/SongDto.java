package Artalia.com.example.MusicBox.Service.Song;

public record SongDto(
        String songLink,
        int songLength,
        String songName,
        String artistName,
        int artist_id
) {}
