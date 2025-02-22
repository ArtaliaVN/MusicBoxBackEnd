package Artalia.com.example.MusicBox.Service.Song;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Artalia.com.example.MusicBox.Service.User.UserEntity;
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

    @Column(length = 25)
    private String songName;
    
    @Column(length = 25)
    private String artistName;

    @Column 
    private String imageURL = "null";

    @Column 
    private String imageID = "null";

    @Column
    private String audioURL = "null";

    @Column 
    private String audioID = "null";

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity artist;
}
