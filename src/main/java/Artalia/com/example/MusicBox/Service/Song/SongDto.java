package Artalia.com.example.MusicBox.Service.Song;

public record SongDto(
        String songName,
        String artistName,
        int user_id,
        String imageURL,
        String imageID,
        String audioURL,
        String audioID
) {}
