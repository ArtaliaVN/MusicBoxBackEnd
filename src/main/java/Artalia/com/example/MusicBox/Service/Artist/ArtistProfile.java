package Artalia.com.example.MusicBox.Service.Artist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ArtistProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "artist_id")
    private ArtistEntity artist;

    public ArtistProfile(){}

    public ArtistProfile(String bio){
        this.bio = bio;
    }

}
