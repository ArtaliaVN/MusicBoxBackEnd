package Artalia.com.example.MusicBox.UI;

import java.awt.Dimension;
import javax.swing.JLabel;

public class SongChip extends CustomizePanel {
    private JLabel cover, songName, artistName;

    public SongChip(int width, int height, int roundness){
        setPreferredSize(new Dimension(width, height));
        setRoundness(roundness);
        cover = new JLabel();
        songName = new JLabel();
        artistName = new JLabel();
    }

    public void addComponent(){
        add(cover);
        add(songName);
        add(artistName);      
    }

    public void setCover(){
        
    }

    public void setArtist(String artistName){
        this.artistName.setText(artistName);
    }

    public void setSongName(String songName){
        this.songName.setText(songName);
    }

    public String getCover(){
        return cover.getText();
    }

    public String getSongName(){
        return songName.getText();
    }

    public String getArtistName(){
        return artistName.getText();
    }
}
