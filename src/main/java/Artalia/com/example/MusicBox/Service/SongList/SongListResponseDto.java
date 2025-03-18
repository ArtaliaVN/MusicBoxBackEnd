package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;

import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SongListResponseDto extends ResponseHandler {
    private int id;
    private String playListName;
    private List<SongEntity> songList;
}
