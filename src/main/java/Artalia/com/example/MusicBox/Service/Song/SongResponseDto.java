package Artalia.com.example.MusicBox.Service.Song;

public record SongResponseDto(
        int id,
        String songName,
        String artistName,
        String imageURL,
        String imageID,
        String audioURL,
        String audioID
) {}
