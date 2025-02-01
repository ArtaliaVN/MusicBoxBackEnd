package Artalia.com.example.MusicBox.Service.Artist;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import Artalia.com.example.MusicBox.Service.User.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ArtistEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 25)
    private String artistName;

    @Column
    private String profileImageURL;

    @Column(unique= true)
    private String email;

    @Column
    private String artistInformation;

    @OneToMany(mappedBy = "artist")
    @JsonManagedReference
    private List<SongEntity> songs;

    
    @ManyToMany
    @JoinColumn(name = "subscribedArtist")
    private List<UserEntity> subscribers;

    @OneToOne(
        mappedBy="artist",
        cascade= CascadeType.ALL
    )
    private ArtistProfile artistProfile;
}
