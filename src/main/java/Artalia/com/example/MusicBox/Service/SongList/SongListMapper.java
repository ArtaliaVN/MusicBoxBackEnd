package Artalia.com.example.MusicBox.Service.SongList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SongListMapper {
    public SongListResponseDto toSongListDto(SongListEntity songList){
        return new SongListResponseDto(songList.getSongListId(), songList.getPlayListName(), songList.getSongList());
    }

    public SongListEntity toSongListEntity(SongListDto songListDto){
        SongListEntity songListEntity = new SongListEntity();
        songListEntity.setPlayListName(songListDto.playListName());
        songListEntity.setSongList(songListDto.songList());
        return songListEntity;
    }

    public List<SongListResponseDto> toSongListDto(List<SongListEntity> songListEntities){
        return songListEntities
                .stream()
                .map(this::toSongListDto)
                .collect(Collectors.toList());
    }
}
