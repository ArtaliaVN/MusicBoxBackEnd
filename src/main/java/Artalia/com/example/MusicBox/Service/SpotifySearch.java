package Artalia.com.example.MusicBox.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import Artalia.com.example.MusicBox.STATS.constant;

@Service
public class SpotifySearch {
    private SpotifyTokenGetter tokenGetter;
    private String url;

    public SpotifySearch(@Value(constant.SpotifySearchURL) String url, SpotifyTokenGetter tokenGetter){
        this.url = url;
        this.tokenGetter = tokenGetter;
    }

    public String searchForArtist(String artistName){
        String artistJson = null;
        try{
        URI uri = new URI(url + "?q=" + artistName + "&type=artist&limit=1");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Bearer " + tokenGetter.getToken())
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        artistJson = response.body();
        //JSONObject result = new JSONObject(response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return artistJson;
    }
}
