package Artalia.com.example.MusicBox.Service.Song;

import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SongResponseDto extends ResponseHandler{
    private int id;
    private String songName;
    private String artistName;
    private String imageURL;
    private String imageID;
    private String audioURL;
    private String audioID;    
}
