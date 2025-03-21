package com.example.Artalia.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Artalia.Data.SongEntity;
import com.example.Artalia.GoogleDrive.DriveService;
import com.example.Artalia.Mapper.SongMapper;
import com.example.Artalia.Model.SongDto;
import com.example.Artalia.Model.SongResponseDto;
import com.example.Artalia.Repository.SongRepository;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, SongMapper songMapper){
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    public SongResponseDto post(SongDto songDto){
        SongEntity songEntity = songMapper.toEntity(songDto);
        songRepository.save((SongEntity) songEntity); 
        SongResponseDto songResponseDto = songMapper.toDto(songEntity);
        return songResponseDto;
    }
    
    public SongResponseDto findById(int id){
        return songMapper.toDto(songRepository.findById(id).orElse(null));
    }
    
    public List<SongResponseDto> findByItemName(String songName){
        return songMapper.toDto(songRepository.findBySongName(songName));
    }

    public List<SongResponseDto> findByUsername(String userName){
        return songMapper.toDto(songRepository.findByArtistName(userName));
    }

    public List<SongResponseDto> findByUserId(int userId){
        return songMapper.toDto(songRepository.findByUserId(userId));
    }

    public List<SongResponseDto> getAll(){
        return songMapper.toDto(songRepository.findAll());
    }
    
    public void deleteById(int id){
        songRepository.deleteById(id);
    }

    public SongResponseDto updateImageById(int id, File image) throws IOException, GeneralSecurityException{
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String imageID = service.uploadImageToFolder("song", image, songEntity.getSongName());
        String imageURL = service.getWebViewLink(imageID);
        songEntity.setImageID(imageID);
        songEntity.setImageURL(imageURL);
        songRepository.save(songEntity);
        return songMapper.toDto(songEntity);
    }
    
    public SongResponseDto updateAudioById(int id, File audio) throws IOException, GeneralSecurityException{
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String audioID = service.uploadAudioToFolder("song", audio, songEntity.getSongName());
        String audioURL = service.getWebViewLink(audioID);
        songEntity.setAudioID(audioID);
        songEntity.setAudioURL(audioURL);
        songRepository.save(songEntity);
        return songMapper.toDto(songEntity);
    }
    
    public byte[] getImageById(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        SongResponseDto songResponseDto = findById(id);
        return service.downloadFromFolder(songResponseDto.getImageID());
    }

    
    public byte[] getAudioById(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        SongResponseDto songResponseDto = findById(id);
        return service.downloadFromFolder(songResponseDto.getAudioID());
    }

    
    public SongRepository getRepo() {
        return songRepository;
    }
}
