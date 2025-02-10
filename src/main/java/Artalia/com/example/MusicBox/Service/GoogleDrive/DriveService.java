package Artalia.com.example.MusicBox.Service.GoogleDrive;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.apache.commons.io.FilenameUtils;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

public class DriveService {
    private static final String folderId = "16HZYufjxA-XXG1NMr0Je5uyv8UYCJhxV";
    private static final String SERVICE_ACOUNT_KEY_PATH = "src\\main\\java\\Artalia\\com\\example\\MusicBox\\Service\\GoogleDrive\\music-box-project-credentials.json";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final String postfixURL = "https://drive.google.com/uc?export=view&id=";

    public String uploadImageToFolder(String prefix, java.io.File file, String name) throws IOException, GeneralSecurityException{
        File metaData = new File();
        metaData.setName(prefix+"_image_"+name);
        metaData.setParents(Collections.singletonList(folderId));
        FileContent mediaContent = new FileContent("image/" + FilenameUtils.getExtension(file.getName()), file);

        try {
            Drive service = createDriveService();
            File uploadedFile = service.files().create(metaData, mediaContent)
                .setFields("id")
                .execute(); 
            String imageId = uploadedFile.getId();
            file.delete();
            return imageId;
          } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }

    public String uploadAudioToFolder(String prefix, java.io.File file, String name) throws IOException, GeneralSecurityException{
        File metaData = new File();
        metaData.setName(prefix+"_audio_"+name);
        metaData.setParents(Collections.singletonList(folderId));
        InputStreamContent mediaContent = new InputStreamContent("audio/" + FilenameUtils.getExtension(file.getName()), new FileInputStream(file));
        try {
            Drive service = createDriveService();
            File uploadedFile = service.files().create(metaData, mediaContent)
                .setFields("id")
                .execute(); 
            String audioID = uploadedFile.getId();
            file.delete();
            return audioID;
          } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }

    public byte[] downloadFromFolder(String imageID) throws IOException, GeneralSecurityException{
        try {
            Drive service = createDriveService();
            OutputStream outputStream = new ByteArrayOutputStream();
            service.files().get(imageID).executeMediaAndDownloadTo(outputStream);
            ByteArrayOutputStream byteOutputStream = (ByteArrayOutputStream) outputStream;
            byte[] result = byteOutputStream.toByteArray();
            return result;
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to move file: " + e.getDetails());
            throw e;
        }
    }

    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
        .createScoped(Collections.singleton(DriveScopes.DRIVE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
        return new Drive.Builder(
            GoogleNetHttpTransport
            .newTrustedTransport(),
            JSON_FACTORY,
            requestInitializer)
            .build();
    }
}
