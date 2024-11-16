package Artalia.com.example.MusicBox.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class SearchPanel extends CustomizePanel {
    private CustomizeJTextField searchField;
    private CustomizePanel searchBtn;
    private ImageIcon searchIcon;
    private JLabel iconLabel;
    
    public SearchPanel(){
        searchField = new CustomizeJTextField();
        searchBtn = new CustomizePanel();
        iconLabel = new JLabel();
        searchBtn.addMouseListener(new SearchBtnController(searchBtn, this));
    }

    public void addComponent(){
        ImageIcon image = new ImageIcon(new ImageIcon("src\\main\\java\\Artalia\\com\\example\\MusicBox\\UI\\ICONS\\searchIcon.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        iconLabel.setIcon(image);
        searchField.setPreferredSize(new Dimension(300,25));
        searchField.setRoundness(10);
        searchBtn.setPreferredSize(new Dimension(25,25));
        searchBtn.setRoundness(10);
        searchBtn.add(iconLabel);
        add(searchField);
        add(searchBtn);
    }

    public CustomizePanel getSearchBtn(){
        return searchBtn;
    }

    public CustomizeJTextField getSearchField(){
        return searchField;
    }

    static class SearchBtnController extends MouseAdapter{
        private JComponent container;
        private SearchPanel searchPanel;

        public SearchBtnController(JComponent container, SearchPanel searchPanel){ 
            this.container = container;
            this.searchPanel = searchPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e){
            
        }

        @Override
        public void mouseEntered(MouseEvent e){
            container.setBackground(Color.GRAY);
        }

        @Override
        public void mouseExited(MouseEvent e){
            container.setBackground(Color.WHITE);
        }
    }
}
