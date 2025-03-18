package Artalia.com.example.MusicBox.Service.Song;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface SongRepository extends JpaRepository<SongEntity, Integer> {
    List<SongEntity> findBySongName(String songName);
    List<SongEntity> findByArtistName(String artistName);
}
 