package Artalia.com.example.MusicBox.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import Artalia.com.example.MusicBox.Control.NavigationBtnController;
import Artalia.com.example.MusicBox.STATS.constant;

public class LeftTool extends CustomizePanel{
    private JLabel label;
    private JLabel settingButton, searchButton, libButton;

    public LeftTool(){
        label = new JLabel();
        settingButton = new JLabel();
        searchButton = new JLabel();
        libButton = new JLabel();
    }   

    public void addComponent(){
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode(constant.secondColor));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(settingButton);
        add(searchButton);
        add(libButton);

        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setText("Music box");
        label.setForeground(Color.WHITE);
        label.setBorder(new EmptyBorder(20, 10, 10, 10));

        settingButton.setText("Setting");
        settingButton.setOpaque(true);
        settingButton.setForeground(Color.WHITE);
        settingButton.setMaximumSize(new Dimension(300, 30));
        settingButton.setBackground(Color.decode(constant.secondColor));
        settingButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        searchButton.setText("Search");
        searchButton.setOpaque(true);
        searchButton.setForeground(Color.WHITE);
        searchButton.setMaximumSize(new Dimension(300, 30));
        searchButton.setBackground(Color.decode(constant.secondColor));
        searchButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        libButton.setText("Library");
        libButton.setOpaque(true);
        libButton.setForeground(Color.WHITE);
        libButton.setMaximumSize(new Dimension(300, 30));
        libButton.setBackground(Color.decode(constant.secondColor));
        libButton.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public JLabel getSettingButton(){
        return settingButton;
    }

    public JLabel getSearchButton(){
        return searchButton;
    }

    public JLabel getLibButton(){
        return libButton;
    }

    public void setMouseAdapter(){
        searchButton.addMouseListener(new NavigationBtnController(constant.SearchCard, searchButton, Color.decode(constant.thirdColor), libButton.getBackground()));
        settingButton.addMouseListener(new NavigationBtnController(constant.SettingCard, settingButton, Color.decode(constant.thirdColor), libButton.getBackground()));
        libButton.addMouseListener(new NavigationBtnController(constant.LibraryCard, libButton, Color.decode(constant.thirdColor), libButton.getBackground()));
    }
}
