package Artalia.com.example.MusicBox.Service.Song;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Artalia.com.example.MusicBox.Service.Artist.ArtistEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SongEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(length = 500)
    private String songLink;

    @Column
    private int songLength;

    @Column(length = 25)
    private String songName;
    
    @Column(length = 25)
    private String artistName;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    private ArtistEntity artist;

    public SongEntity(){
        super();
    }

    public SongEntity(String songLink, String songName, String artistName, int songLength){
        this.songLink = songLink;
        this.songName = songName;
        this.artistName = artistName;
        this.songLength = songLength;
    }

    public SongEntity(int id, String songLink, String songName, String artistName, int songLength){
        this.id = id;
        this.songLink = songLink;
        this.songName = songName;
        this.artistName = artistName;
        this.songLength = songLength;
    }
    public void setSongId(int id){
        this.id = id;
    }

    public void setSongLink(String songLink){
        this.songLink = songLink;
    }

    public void setSongLength(int songLength){
        this.songLength = songLength;
    }

    public void setSongName(String songName){
        this.songName = songName;
    }
    
    public void setArtistName(String artistName){
        this.artistName = artistName;
    }

    public void setArtist(ArtistEntity artist){
        this.artist = artist;
    }

    public int getSongID(){
        return id;
    }

    public String getSongLink(){
        return songLink;
    }

    public int getSongLength(){
        return songLength;
    }

    public String getSongName(){
        return songName;
    }
    
    public String getArtistName(){
        return artistName;
    }

    public ArtistEntity getArtist(){
        return artist;
    }
}
