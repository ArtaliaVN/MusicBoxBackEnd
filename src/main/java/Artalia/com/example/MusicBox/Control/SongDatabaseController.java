package Artalia.com.example.MusicBox.Control;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Artalia.com.example.MusicBox.Service.ArtistEntity;
import Artalia.com.example.MusicBox.Service.SongDto;
import Artalia.com.example.MusicBox.Service.SongEntity;
import Artalia.com.example.MusicBox.Service.SongRepository;
import Artalia.com.example.MusicBox.Service.SongResponseDto;

@RestController
public class SongDatabaseController {
    private final SongRepository songRepository;

    public SongDatabaseController(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public SongEntity toSongEntity(SongDto songDto){
        SongEntity songEntity = new SongEntity();
        songEntity.setLink(songDto.link());
        songEntity.setSongName(songDto.songName());
        songEntity.setArtistName(songDto.artistName());
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setArtistID(songDto.artist_id());
        songEntity.setArtist(artistEntity);
        return songEntity;
    }

    private SongResponseDto toSongDto(SongEntity songEntity){
        SongResponseDto songResponseDto = new SongResponseDto(songEntity.getLink(), songEntity.getSongName(), songEntity.getArtistName());
        return songResponseDto;
    }

    @PostMapping("/song/post")
    public SongResponseDto post(@RequestBody SongDto songDto){
        SongEntity songEntity = toSongEntity(songDto);
        SongResponseDto songResponseDto = toSongDto(songEntity);
        songRepository.save(songEntity);
        return songResponseDto;
    }

    @GetMapping("/song/get/id={id}")
    public SongResponseDto getById(@PathVariable("id") int id){
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        SongResponseDto songResponseDto = toSongDto(songEntity);
        return songResponseDto;
    }

    /* 
    @GetMapping("/song/get/artistname={artistname}")
    public List<SongEntity> getByArtistName(@PathVariable String artistname){
        return songRepository.findAll(artistname);
    }*/

    @GetMapping("/song/get/all")
    public List<SongResponseDto> getAll(){
        return songRepository.findAll()
                .stream()
                .map(this::toSongDto)
                .collect(Collectors.toList());
    }
}
