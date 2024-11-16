package Artalia.com.example.MusicBox.UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

import Artalia.com.example.MusicBox.STATS.constant;

public class SearchCard extends CustomizePanel {
    private SearchPanel searchPanel;
    private DisplayPanel displayPanel;

    public SearchCard(){
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        searchPanel = new SearchPanel();
        displayPanel = new DisplayPanel();
        setLayout(box);
    }

    public void addComponent(){
        add(searchPanel);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(displayPanel);
        setBackground(Color.decode(constant.firstColor));

        searchPanel.setMaximumSize(new Dimension(5000, constant.searchPanelHeight));
        searchPanel.addComponent();
        searchPanel.setRoundness(10);
        searchPanel.setBackground(Color.decode(constant.secondColor));
        
        displayPanel.setBackground(Color.decode(constant.secondColor));
        displayPanel.setRoundness(10);
    }

    public SearchPanel getSearchPanel(){
        return searchPanel;
    }
}
