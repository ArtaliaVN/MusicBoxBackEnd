package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends ResponseHandler{
    private int id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String imageURL;
    private String imageID;
    private List<SongEntity> songs;
    private List<SongListEntity> songListLibrary;
}
