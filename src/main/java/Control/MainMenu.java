package Control;

import Control.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainMenu {
    private JRadioButton rMap1;
    private JRadioButton rMap2;
    private JRadioButton rPlayers4;
    private JRadioButton rPlayers5;
    private JRadioButton rPlayers6;
    private JLabel lMapSelect;
    private JLabel lPlayerSelect;
    private JButton bStartGame;
    private JLabel lMap1;
    private JLabel lMap2;
    private Game game;
    private GameMenu gameMenu;

    public MainMenu(Game game){
        this.game = game;
        init();
    }

    public MainMenu(Game game, GameMenu gameMenu){
        this.game = game;
        this.gameMenu = gameMenu;
        init();
    }

    /**
     * Létrehozza az ablakot
     */
    public void init(){
        JFrame newGame = new JFrame("New Game");
        newGame.setSize(300,500);
        JPanel jPanel = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        JPanel maps = new JPanel(new GridLayout(0,1));
        JPanel map1 = new JPanel(new BorderLayout());
        JPanel map2 = new JPanel(new BorderLayout());
        JPanel bottom = new JPanel(new BorderLayout());
        JPanel playres = new JPanel(new GridLayout(0,3));

        rMap1 = new JRadioButton("Map1");
        rMap2 = new JRadioButton("Map2");
        final ButtonGroup mapbuttons = new ButtonGroup();
        mapbuttons.add(rMap1);
        mapbuttons.add(rMap2);
        rMap1.setSelected(true);

        rPlayers4 = new JRadioButton("4 playres");
        rPlayers5 = new JRadioButton("5 playres");
        rPlayers6 = new JRadioButton("6 playres");
        final ButtonGroup playerbuttons = new ButtonGroup();
        playerbuttons.add(rPlayers4);
        playerbuttons.add(rPlayers5);
        playerbuttons.add(rPlayers6);
        rPlayers4.setSelected(true);

        try {
            ImageIcon iimap1 = new ImageIcon(ImageIO.read(new File("images/map1.png")));
            lMap1 = new JLabel(iimap1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIcon iimap2 = new ImageIcon(ImageIO.read(new File("images/map2.png")));
            lMap2 = new JLabel(iimap2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lMapSelect = new JLabel("Map select");
        lPlayerSelect = new JLabel("Players count");

        /**
         * A játék indítására szolgáló gomb és annak action listener-je
         */
        bStartGame = new JButton("Start Game");
        bStartGame.addActionListener(e -> {
            int map = 0;
            int player = 0;
            if(rMap1.isSelected()){
                map = 1;
            }
            if(rMap2.isSelected()){
                map = 2;
            }
            if(rPlayers4.isSelected()){
                player = 4;
            }
            if(rPlayers5.isSelected()){
                player = 5;
            }
            if(rPlayers6.isSelected()){
                player = 6;
            }
            game.getMap().setMapNumber(map);
            game.getMap().setVirologistNumber(player);
            game.startGame();
            newGame.setVisible(false);
            if(gameMenu != null){
                gameMenu.getFrame().setVisible(false);
            }
            gameMenu = new GameMenu(game);
        });

        map1.add(rMap1, BorderLayout.PAGE_START);
        map1.add(lMap1, BorderLayout.CENTER);

        map2.add(rMap2, BorderLayout.PAGE_START);
        map2.add(lMap2, BorderLayout.CENTER);

        maps.add(map1);
        maps.add(map2);

        top.add(lMapSelect, BorderLayout.PAGE_START);
        top.add(maps, BorderLayout.CENTER);

        playres.add(rPlayers4);
        playres.add(rPlayers5);
        playres.add(rPlayers6);

        bottom.add(lPlayerSelect, BorderLayout.PAGE_START);
        bottom.add(playres, BorderLayout.CENTER);
        bottom.add(bStartGame, BorderLayout.PAGE_END);

        jPanel.add(top, BorderLayout.CENTER);
        jPanel.add(bottom, BorderLayout.PAGE_END);
        newGame.add(jPanel);

        newGame.setVisible(true);
        if(gameMenu == null){
            newGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
