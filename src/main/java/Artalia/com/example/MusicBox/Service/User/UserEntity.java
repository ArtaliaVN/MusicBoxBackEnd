package Artalia.com.example.MusicBox.Service.User;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Artalia.com.example.MusicBox.Service.SongList.SongListEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
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

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<SongListEntity> songListLibrary;

    public UserEntity(){
        super();
    }

    public UserEntity(String email, String userName, String firstName, String lastName, String password){
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPassWord(String passWord){
        this.password = passWord;
    }

    public void setSongLibrary(List<SongListEntity> songListLibrary){
        this.songListLibrary = songListLibrary;
    }

    public int getUserId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getUserName(){
        return userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPassWord(){
        return password;
    }

    public List<SongListEntity> getSongLibrary(){
        return songListLibrary;
    }
}
