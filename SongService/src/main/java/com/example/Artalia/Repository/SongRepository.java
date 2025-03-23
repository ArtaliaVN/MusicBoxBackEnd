package com.example.Artalia.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Artalia.Data.SongEntity;

import reactor.core.publisher.Flux;

@Repository
public interface SongRepository extends ReactiveCrudRepository<SongEntity, Integer> {
    Flux<SongEntity> findBySongname(String songname);
    Flux<SongEntity> findByArtistname(String artistname);
    Flux<SongEntity> findByUserid(int userid);
}
 