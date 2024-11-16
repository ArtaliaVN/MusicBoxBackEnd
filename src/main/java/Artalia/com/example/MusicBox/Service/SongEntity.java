package Artalia.com.example.MusicBox.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private String link;

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

    public SongEntity(String link, String songName, String artistName){
        this.link = link;
        this.songName = songName;
        this.artistName = artistName;
    }

    public SongEntity(int id, String link, String songName, String artistName){
        this.id = id;
        this.link = link;
        this.songName = songName;
        this.artistName = artistName;
    }

    public void setLink(String link){
        this.link = link;
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

    public String getLink(){
        return link;
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
