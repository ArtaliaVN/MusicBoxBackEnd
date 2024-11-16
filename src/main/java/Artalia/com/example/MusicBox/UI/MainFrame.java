package Artalia.com.example.MusicBox.UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import Artalia.com.example.MusicBox.STATS.constant;

public class MainFrame {
    private BorderLayout borderLayout;
    private JFrame frame;
    private LeftTool leftTool;
    private CentralScreen centralScreen;

    private SearchCard searchFrame;
    private PlayCard playFrame;
    private LibraryCard libraryCard;
    private SettingCard settingCard;
    
    public MainFrame () {
        frame = new JFrame();
        leftTool = new LeftTool();
        centralScreen = new CentralScreen();
        searchFrame = new SearchCard();
        playFrame = new PlayCard();
        libraryCard = new LibraryCard();
        settingCard = new SettingCard();
        borderLayout = new BorderLayout();
        borderLayout.setHgap(5);
        borderLayout.setVgap(5);

        frame.setTitle("Music box");
        frame.setSize(constant.initializeWidth, constant.initializeHeight);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(borderLayout);
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode(constant.firstColor)));
        frame.getContentPane().setBackground(Color.decode(constant.firstColor));
        frame.setMinimumSize(new Dimension(constant.initializeWidth, constant.initializeHeight));

        searchFrame.addComponent();

        centralScreen.addCard(constant.SearchCard, searchFrame);
        centralScreen.addCard(constant.PlayCard, playFrame);
        centralScreen.addCard(constant.LibraryCard, libraryCard);
        centralScreen.addCard(constant.SettingCard, settingCard);
        leftTool.setRoundness(10);
        leftTool.addComponent();
        leftTool.setMouseAdapter();
        frame.add(leftTool, BorderLayout.WEST);
        frame.add(centralScreen, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                Point coord = new Point();
                coord = frame.getContentPane().getMousePosition(true);
                System.out.println(coord);
                if(searchFrame.getSearchPanel().getSearchField().getBounds().contains(coord))
                    searchFrame.getSearchPanel().getSearchField().setEnabled(true);
                else
                    searchFrame.getSearchPanel().getSearchField().setEnabled(false);
                System.out.println(searchFrame.getSearchPanel().getSearchField().getBounds().contains(coord));
            }
        });

    }
}
