package Artalia.com.example.MusicBox.Service.Song;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.GoogleDrive.DriveService;

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
        songRepository.save(songEntity);
        SongResponseDto songResponseDto = songMapper.toSongDto(songEntity);
        return songResponseDto;
    }

    public SongResponseDto getById(int id){
        return songMapper.toSongDto(songRepository.findById(id).orElse(null));
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

    public void deleteById(int id){
        songRepository.deleteById(id);
    }

    public SongResponseDto updateImageById(int id, File image) throws IOException, GeneralSecurityException{
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String imageID = service.uploadImageToFolder("song", image, image.getName());
        String imageURL = DriveService.postfixURL + imageID;
        songEntity.setImage(imageID);
        songEntity.setImageURL(imageURL);
        songRepository.save(songEntity);
        return songMapper.toSongDto(songEntity);
    }

    public SongResponseDto updateAudioById(int id, File audio) throws IOException, GeneralSecurityException{
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String imageID = service.uploadImageToFolder("song", audio, audio.getName());
        String imageURL = DriveService.postfixURL + imageID;
        songEntity.setImage(imageID);
        songEntity.setImageURL(imageURL);
        songRepository.save(songEntity);
        return songMapper.toSongDto(songEntity);
    }

    public byte[] getImageBySongID(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        SongResponseDto songResponseDto = getById(id);
        return service.downloadFromFolder(songResponseDto.imageID());
    }

    public byte[] getAudioBySongID(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        SongResponseDto songResponseDto = getById(id);
        return service.downloadFromFolder(songResponseDto.audioID());
    }
}
