package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SongListService {
    private final SongListRepository songListRepository;
    private final SongListMapper songListMapper;

    public SongListService(SongListRepository songListRepository, SongListMapper songListMapper){
        this.songListRepository = songListRepository;
        this.songListMapper = songListMapper;
    }

    public SongListResponseDto postSongList(SongListDto songListDto){
        SongListEntity songListEntity = songListMapper.toSongListEntity(songListDto);
        SongListResponseDto songListResponseDto = songListMapper.toSongListDto(songListEntity);
        songListRepository.save(songListEntity);
        return songListResponseDto;
    }

    public SongListResponseDto getById(int Id){
        return songListMapper.toSongListDto(songListRepository.findById(Id).orElse(null));
    }

    public List<SongListResponseDto> getAll(){
        return songListMapper.toSongListDto(songListRepository.findAll());
    } 
}
