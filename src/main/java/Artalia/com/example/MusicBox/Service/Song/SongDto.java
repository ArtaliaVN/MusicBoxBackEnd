package Artalia.com.example.MusicBox.Service.Song;

import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SongDto extends RequestHandler {
    private String songName;
    private String artistName;
    private int user_id;
}
