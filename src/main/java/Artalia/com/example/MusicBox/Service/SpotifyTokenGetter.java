package Artalia.com.example.MusicBox.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import Artalia.com.example.MusicBox.STATS.constant;

@Service
public class SpotifyTokenGetter {
    @Value(constant.SpotifyTokenURI)
    private String url;
    @Value(constant.SpotifyClientID)
    private String ID;
    @Value(constant.SpotifyClientSecret)
    private String Secret;

    private String token;

    public void createToken(){
        String token = null;
        try {
        URI uri = new URI(url);
        String auth_String = ID + ":" + Secret;
        String auth_Base64 = Base64.getEncoder().encodeToString(auth_String.getBytes());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization","Basic " + auth_Base64)
                .header("content-type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject result = new JSONObject(response.body());
        token = result.getString("access_token");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.token = token;
    }

    public String getToken(){
        if(token == null){
            createToken();
        }
        return token;
    }

    public String get_Auth_Header(String token){
        return "{\"Authorization\":\"Bearer " + token + "\"}";
    }
}
