package com.example.Artalia.GoogleDrive;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.apache.commons.io.FilenameUtils;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

public class DriveService {
    private static final String folderId = "16HZYufjxA-XXG1NMr0Je5uyv8UYCJhxV";
    private static final String SERVICE_ACOUNT_KEY_PATH = "src\\main\\java\\com\\example\\Artalia\\GoogleDrive\\music-box-project-credentials.json";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

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
        FileContent mediaContent = new FileContent("audio/mpeg", file);
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

    public String uploadBigAudioToFolder(String prefix, java.io.File file, String name) throws IOException, GeneralSecurityException{
        File metaData = new File();
        metaData.setName(prefix+"_audio_"+name);
        metaData.setParents(Collections.singletonList(folderId));
        FileContent mediaContent = new FileContent("audio/mpeg", file);
        try {
            Drive service = createDriveService();
            Drive.Files.Create createFile = service.files().create(metaData, mediaContent).setFields("id"); 
            MediaHttpUploader uploader = createFile.getMediaHttpUploader();
            uploader.setDirectUploadEnabled(false);
            uploader.setProgressListener((MediaHttpUploader uploader1) -> {
                throw new UnsupportedOperationException("Not supported yet.");
            });
            File uploadedFile = createFile.execute();
            String audioID = uploadedFile.getId();
            file.delete();
            return audioID;
          } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }

    public void deleteFileFromFolder(String ID) throws GeneralSecurityException, IOException{
        try {
            Drive service = createDriveService();
            service.files().delete(ID).execute();
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to delete file: " + e.getDetails());
        }
    }

    public byte[] downloadFromFolder(String imageID) throws IOException, GeneralSecurityException{
        try {
            Drive service = createDriveService();
            BufferedInputStream inputStream = new BufferedInputStream(service.files().get(imageID).setFields("*").executeMediaAsInputStream());
            byte[] result = inputStream.readAllBytes();
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

    public String getWebViewLink(String ID){
        String postfix = "/view?usp=drivesdk";
        String prefix = "https://drive.google.com/file/d/";
        return prefix + ID + postfix;
    }
}
