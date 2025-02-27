package Artalia.com.example.MusicBox.Service.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Artalia.com.example.MusicBox.Service.Role.RoleEntity;
import Artalia.com.example.MusicBox.Service.Song.SongEntity;
import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Email
    private String email;

    @Column(unique= true)
    @NotBlank
    private String userName;

    @Column 
    @NotBlank
    private String firstName;

    @Column 
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
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

    @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        fetch = FetchType.EAGER  
    )
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
