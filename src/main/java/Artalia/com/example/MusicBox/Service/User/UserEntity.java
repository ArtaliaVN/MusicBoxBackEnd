package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UserEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique= true)
    private String email;

    @Column(unique= true)
    private String userName;

    @Column 
    private String firstName;

    @Column 
    private String lastName;

    @Column
    private String password;

    @Column 
    private String imageURL = "https://drive.google.com/file/d/14YmUPBwmEkuvUee00levMbocNz-XvkWs/view?usp=drivesdk";

    @Column 
    private String imageID = "14YmUPBwmEkuvUee00levMbocNz-XvkWs";

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<SongListEntity> songListLibrary;

    @OneToMany(mappedBy = "artist")
    @JsonManagedReference
    private List<SongEntity> songs;
}
