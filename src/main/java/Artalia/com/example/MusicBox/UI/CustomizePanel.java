package Artalia.com.example.MusicBox.UI;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class CustomizePanel extends JPanel {
    private int roundness = 0;
    
    public CustomizePanel(){
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(creatRoundness());
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
    }

    public void setRoundness(int roundness){
        this.roundness = roundness;
        repaint();
    }   

    private Shape creatRoundness(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(roundness, width);
        int roundY = Math.min(roundness, height);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        return area;
    } 
}
