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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SongEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String songLink;

    @Column
    private String coverImageURL;

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
}
