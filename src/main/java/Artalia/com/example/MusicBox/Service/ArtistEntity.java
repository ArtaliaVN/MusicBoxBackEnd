package Artalia.com.example.MusicBox.Service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 25)
    private String artistName;

    @Column(unique= true)
    private String email;

    @Column
    private String artistInformation;

    @OneToMany(mappedBy = "artist")
    @JsonManagedReference
    private List<SongEntity> songs;

    @OneToOne(
        mappedBy="artist",
        cascade= CascadeType.ALL
    )
    private ArtistProfile artistProfile;

    public ArtistEntity(){
        super();
    }

    public ArtistEntity(String artistName, String email ,String artistInformation){
        this.artistName = artistName;
        this.email = email;
        this.artistInformation = artistInformation;
    }
    
    public ArtistEntity(int id, String artistName, String email, String artistInformation){
        this.id = id;
        this.artistName = artistName;
        this.email = email;
        this.artistInformation = artistInformation;
    }

    public void setArtistID(int id){
        this.id = id;
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
    }

    public void setArtistInformation(String artistInformation){
        this.artistInformation = artistInformation;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSongs(List<SongEntity> songs){
        this.songs = songs;
    }

    public void setArtistProfile(ArtistProfile artistProfile){
        this.artistProfile = artistProfile;
    }

    public int getArtistID(){
        return id;
    }

    public String getArtistName(){
        return artistName;
    }

    public String getArtistInformation(){
        return artistInformation;
    }

    public String getEmail(){
        return email;
    }

    public List<SongEntity> getSongs(){
        return songs;
    }

    public ArtistProfile getArtistProfile(){
        return artistProfile;
    }
}
