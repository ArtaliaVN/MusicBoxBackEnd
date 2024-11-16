package Artalia.com.example.MusicBox.UI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JComponent;

import Artalia.com.example.MusicBox.Control.NavigationBtnManager;
import Artalia.com.example.MusicBox.STATS.constant;

public class CentralScreen extends CustomizePanel {
    private NavigationBtnManager navigationBtnManager;
    private CardLayout card;
    public CentralScreen(){
        card = new CardLayout();
        navigationBtnManager = new NavigationBtnManager();
        navigationBtnManager.setCentralScreen(this);
        setLayout(card);
        setBackground(Color.decode(constant.firstColor));
    }

    public void addCard(String cardName, JComponent cardPanel){
        add(cardName, cardPanel);
    }

    public void switchCard(String cardName){
        card.show(this, cardName);
    }
}
