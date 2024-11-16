package Artalia.com.example.MusicBox.Control;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class NavigationBtnController extends MouseAdapter {
    private JComponent container;
    private Color enterColor;
    private Color exitColor;
    private String buttonFuncName;
    private NavigationBtnManager navigationBtnManager;

    public NavigationBtnController (String buttonFuncName, JComponent container, Color enterColor, Color exitColor){
        navigationBtnManager = new NavigationBtnManager();
        this.container = container;
        this.enterColor = enterColor;
        this.exitColor = exitColor;
        this.buttonFuncName = buttonFuncName;
    }

    public String getFuncName(){
        return buttonFuncName;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        navigationBtnManager.changeCentralScreen(buttonFuncName);
    }

    @Override 
    public void mouseEntered(MouseEvent e){
        container.setBackground(enterColor);
    }

    @Override
    public void mouseExited(MouseEvent e){
        container.setBackground(exitColor);
    }
}
