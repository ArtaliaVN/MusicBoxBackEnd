package Artalia.com.example.MusicBox.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer>{
    List<ArtistEntity> findByArtistName(String artistName);
}
