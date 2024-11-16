package Artalia.com.example.MusicBox.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomizeJScrollBarUI extends BasicScrollBarUI {
    
    public CustomizeJScrollBarUI(){}
    
    /* 
    @Override
    protected Dimension getMaximumThumbSize(){
        return switch (scrollbar.getOrientation()) {
            case JScrollBar.VERTICAL -> new Dimension(0,constant.thumbSize);
            default -> new Dimension(constant.thumbSize, 0);
        };
    }

    @Override
    protected Dimension getMinimumThumbSize(){
        return switch (scrollbar.getOrientation()) {
            case JScrollBar.VERTICAL -> new Dimension(0,constant.thumbSize);
            default -> new Dimension(constant.thumbSize, 0);
        };
    }
    */

    @Override
    protected JButton createIncreaseButton(int i){
        return new CustomizeJScrollButton();
    }

    @Override
    protected JButton createDecreaseButton(int i){
        return new CustomizeJScrollButton();
    }


    @Override
    protected void paintTrack(Graphics g, JComponent component, Rectangle rect){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int orientation = scrollbar.getOrientation();
        int size, x, y, width, height;
        switch(orientation){
            case JScrollBar.VERTICAL -> {
                size = rect.width;
                x = rect.x+((rect.width-size)/2);
                y = rect.y;
                width = size;
                height = rect.height;
            }
            default -> {
                size = rect.height;
                x = 0;
                y = rect.y+((rect.height-size)/2);
                width = rect.width;
                height = size;
            }
        }
        g2D.fillRect(x, y, width, height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent component, Rectangle rect){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = rect.x;
        int y = rect.y;
        int width = rect.width;
        int height = rect.height;
        switch(scrollbar.getOrientation()){
            case JScrollBar.VERTICAL -> {
                y += 8;
                height -= 16;
            }
            default -> {
                x += 8;
                width -= 16;
            }
        }
        g2D.setColor(scrollbar.getForeground());
        g2D.fillRoundRect(x, y, width, height, 10, 10);
    }
}

class CustomizeJScrollButton extends JButton {
    public CustomizeJScrollButton(){
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void paint(Graphics g){}
}
