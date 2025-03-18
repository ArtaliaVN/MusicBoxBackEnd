package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.MapperHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;

@Service
public class SongListService{
    private final SongListRepository songListRepository;
    private final MapperHandler songListMapper;

    public SongListService(SongListRepository songListRepository, SongListMapper songListMapper){
        this.songListRepository = songListRepository;
        this.songListMapper = songListMapper;
    }

    public ResponseHandler postSongList(SongListDto songListDto){
        EntityHandler songListEntity = songListMapper.toEntity(songListDto);
        ResponseHandler songListResponseDto = songListMapper.toDto(songListEntity);
        songListRepository.save((SongListEntity) songListEntity);
        return (SongListResponseDto) songListResponseDto;
    }

    public ResponseHandler getById(int Id){
        return songListMapper.toDto(songListRepository.findById(Id).orElse(null));
    }

    public List<? extends ResponseHandler> getAll(){
        return songListMapper.toDto(songListRepository.findAll());
    } 

    public void deleteById(int id){
        songListRepository.deleteById(id);
    }
}
