package Artalia.com.example.MusicBox.Service.Song;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, SongMapper songMapper){
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    public SongResponseDto postSong(SongDto songDto){
        SongEntity songEntity = songMapper.toSongEntity(songDto);
        SongResponseDto songResponseDto = songMapper.toSongDto(songEntity);
        songRepository.save(songEntity);
        return songResponseDto;
    }

    public SongResponseDto getById(int id){
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        return songMapper.toSongDto(songEntity);
    }

    public List<SongResponseDto> getBySongName(String songName){
        return songMapper.toSongDto(songRepository.findBySongName(songName));
    }

    public List<SongResponseDto> getByArtistName(String artistName){
        return songMapper.toSongDto(songRepository.findByArtistName(artistName));
    }

    public List<SongResponseDto> getAll(){
        return songMapper.toSongDto(songRepository.findAll());
    }
}
