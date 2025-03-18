package Artalia.com.example.MusicBox.Service.Song;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import Artalia.com.example.MusicBox.Service.GoogleDrive.DriveService;
import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.ItemServiceHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.MapperHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.RequestHandler;
import Artalia.com.example.MusicBox.Service.ServiceInterface.ResponseHandler;
import reactor.core.publisher.Mono;

@Service
public class SongService implements ItemServiceHandler{
    private final SongRepository songRepository;
    private final MapperHandler songMapper;

    public SongService(SongRepository songRepository, SongMapper songMapper){
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Override
    public ResponseHandler post(RequestHandler request){
        SongDto songDto = (SongDto) request;
        EntityHandler songEntity = songMapper.toEntity(songDto);
        songRepository.save((SongEntity) songEntity); 
        ResponseHandler songResponseDto = songMapper.toDto(songEntity);
        return songResponseDto;
    }

    @Override
    public ResponseHandler findById(int id){
        return songMapper.toDto(songRepository.findById(id).orElse(null));
    }

    @Override
    public List<? extends ResponseHandler> findByItemName(String songName){
        return songMapper.toDto(songRepository.findBySongName(songName));
    }

    @Override
    public List<? extends ResponseHandler> findByUsername(String userName){
        return songMapper.toDto(songRepository.findByArtistName(userName));
    }

    @Override
    public List<? extends ResponseHandler> getAll(){
        return songMapper.toDto(songRepository.findAll());
    }

    @Override
    public void deleteById(int id){
        songRepository.deleteById(id);
    }

    @Override
    public ResponseHandler updateImageById(int id, File image) throws IOException, GeneralSecurityException{
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String imageID = service.uploadImageToFolder("song", image, songEntity.getSongName());
        String imageURL = service.getWebViewLink(imageID);
        songEntity.setImageID(imageID);
        songEntity.setImageURL(imageURL);
        songRepository.save(songEntity);
        return songMapper.toDto(songEntity);
    }

    @Override
    public ResponseHandler updateAudioById(int id, File audio) throws IOException, GeneralSecurityException{
        SongEntity songEntity = songRepository.findById(id).orElse(null);
        DriveService service = new DriveService();
        String audioID = service.uploadAudioToFolder("song", audio, songEntity.getSongName());
        String audioURL = service.getWebViewLink(audioID);
        songEntity.setAudioID(audioID);
        songEntity.setAudioURL(audioURL);
        songRepository.save(songEntity);
        return songMapper.toDto(songEntity);
    }

    @Override
    public byte[] getImageById(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        SongResponseDto songResponseDto = (SongResponseDto) findById(id);
        return service.downloadFromFolder(songResponseDto.getImageID());
    }

    @Override
    public byte[] getAudioById(int id) throws IOException, GeneralSecurityException{
        DriveService service = new DriveService();
        SongResponseDto songResponseDto = (SongResponseDto) findById(id);
        return service.downloadFromFolder(songResponseDto.getAudioID());
    }

    public Mono<Resource> getSongAudioByID(int id){
        SongResponseDto songResponseDto = (SongResponseDto) findById(id);
        return Mono.fromSupplier(()-> new PathResource(songResponseDto.getAudioURL()));
    }

    @Override
    public SongRepository getRepo() {
        return songRepository;
    }
}
