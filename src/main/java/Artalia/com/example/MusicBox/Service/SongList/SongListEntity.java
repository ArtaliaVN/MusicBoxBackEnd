package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import Artalia.com.example.MusicBox.Service.User.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class SongListEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String playListName;

    @OneToMany 
    private List<SongEntity> songList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    public SongListEntity(){
        super();
    };

    public SongListEntity(String playListName){
        this.playListName = playListName;
    }

    public void setPlayListName(String playListName){
        this.playListName = playListName;
    }

    public void setSongList(List<SongEntity> songList){
        this.songList = songList;
    }

    public void setUserEntity(UserEntity user){
        this.user = user;
    }

    public int getSongListId(){
        return id;
    }

    public String getPlayListName(){
        return playListName;
    }

    public List<SongEntity> getSongList(){
        return songList;
    }

    public UserEntity getUserEntity(){
        return user;
    }
}
