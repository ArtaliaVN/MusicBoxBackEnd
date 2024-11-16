package Artalia.com.example.MusicBox.UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

import Artalia.com.example.MusicBox.STATS.constant;

public class CustomizeJScrollBar extends JScrollBar{
    public CustomizeJScrollBar(){
        setUI(new CustomizeJScrollBarUI());
        setPreferredSize(new Dimension(8,8));
        setForeground(Color.decode(constant.firstColor));
        setBackground(Color.decode(constant.secondColor));
    }
}
