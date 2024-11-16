package Artalia.com.example.MusicBox.UI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Artalia.com.example.MusicBox.STATS.constant;

public class DisplayPanel extends CustomizePanel {
    private CustomizePanel toolBar;
    private JScrollPane jScrollPane;
    private JPanel panel;
    
    public DisplayPanel(){
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setBackground(Color.decode(constant.secondColor));
        panel.setLayout(new WrapLayout(WrapLayout.LEADING));
        jScrollPane = new JScrollPane(panel);
        jScrollPane.setOpaque(false);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBar(new CustomizeJScrollBar());
        add(jScrollPane, BorderLayout.CENTER);
    }

    public void addCard(JComponent component){
        panel.add(component);
    }
}
