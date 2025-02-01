package Artalia.com.example.MusicBox.Service.Song;

public record SongDto(
        String songLink,
        String coverImageURL,
        int songLength,
        String songName,
        String artistName,
        int artist_id
) {}
