package Artalia.com.example.MusicBox.Service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Integer> {}
