package com.example.Artalia.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Artalia.Data.SongEntity;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer> {
    List<SongEntity> findBySongName(String songName);
    List<SongEntity> findByArtistName(String artistName);
    List<SongEntity> findByUserId(int userId);
}
 