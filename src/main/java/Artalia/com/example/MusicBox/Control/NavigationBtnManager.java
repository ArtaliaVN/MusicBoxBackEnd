package Artalia.com.example.MusicBox.Control;

import Artalia.com.example.MusicBox.UI.CentralScreen;
import Artalia.com.example.MusicBox.UI.SearchPanel;

public class NavigationBtnManager {
    private static CentralScreen centralScreen;
    private SearchPanel searchPanel;

    public NavigationBtnManager(){}

    public void setCentralScreen(CentralScreen centralScreen){
        this.centralScreen = centralScreen;
    }

    public void changeCentralScreen(String cardName){
        centralScreen.switchCard(cardName);
    }
}
