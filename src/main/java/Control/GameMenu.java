package Control;

import Control.Game;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameMenu implements ActionListener {
    private JOptionPane jPopup;
    private JButton bBag;
    private JButton bGeneticCodes;
    private JButton bCollect;
    private JButton bWear;
    private JButton bEndTurn;
    private JLabel lBag;
    private JLabel lGeneticCodes;
    private JLabel lCollect;
    private JLabel lWear;
    private JLabel lVirologistStats;
    private JPanel pVirologistStats;
    private JPanel virologistout;
    private JFrame fGame;
    private GamePanel leftPanel;
    private Game game;

    public GameMenu(Game game){
        this.game = game;
        init();
    }

    /**
     * Visszaadja az ablakot
     * @return
     */
    public JFrame getFrame() {
        return fGame;
    }

    /**
     * Létrehozza az ablakot amiben ott lesz a játék, valamint a gombok és a virológus statjai
     */
    public void init(){
        fGame = new JFrame("VakVirologusok");
        fGame.setSize(new Dimension(1280,720));
        fGame.setResizable(false);

        final JMenuBar jMenuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game", KeyEvent.VK_P);
        newGame.setActionCommand("newgame");
        newGame.addActionListener(this);
        gameMenu.add(newGame);
        jMenuBar.add(gameMenu);
        fGame.setJMenuBar(jMenuBar);

        JPanel table = new JPanel();
        table.setBackground(Color.BLACK);
        fGame.add(table, BorderLayout.CENTER);
        JPanel rightSide = new JPanel(new GridLayout(0,1));
        JPanel buttons = new JPanel(new GridLayout(0,1));
        lBag = new JLabel("Open Bag");
        bBag = new JButton("Bag");
        bBag.setActionCommand("bag");
        bBag.addActionListener(this);

        lGeneticCodes = new JLabel("Open Genteic Codes");
        bGeneticCodes = new JButton("Genteic Codes");
        bGeneticCodes.setActionCommand("codes");
        bGeneticCodes.addActionListener(this);

        lCollect = new JLabel("Collect collectable");
        bCollect = new JButton("Collect");
        bCollect.setActionCommand("collect");
        bCollect.addActionListener(this);

        lWear = new JLabel("Open wear");
        bWear = new JButton("Wear");
        bWear.setActionCommand("wear");
        bWear.addActionListener(this);

        bEndTurn = new JButton("End Turn");
        bEndTurn.setActionCommand("endturn");
        bEndTurn.addActionListener(this);

        lVirologistStats = new JLabel("Virologist stats:");
        pVirologistStats = new JPanel(new BorderLayout());
        virologistout = initStats();

        buttons.add(lBag);
        buttons.add(bBag);
        buttons.add(lGeneticCodes);
        buttons.add(bGeneticCodes);
        buttons.add(lCollect);
        buttons.add(bCollect);
        buttons.add(lWear);
        buttons.add(bWear);
        buttons.add(new JLabel(""));
        buttons.add(bEndTurn);
        rightSide.add(buttons);

        pVirologistStats.add(lVirologistStats, BorderLayout.PAGE_START);
        pVirologistStats.add(virologistout, BorderLayout.CENTER);
        rightSide.add(pVirologistStats);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(rightSide, BorderLayout.CENTER);
        rightPanel.add(new JPanel(), BorderLayout.PAGE_END);
        rightPanel.add(new JPanel(), BorderLayout.PAGE_START);
        rightPanel.add(new JPanel(), BorderLayout.LINE_START);
        rightPanel.add(new JPanel(), BorderLayout.LINE_END);
        fGame.add(rightPanel, BorderLayout.LINE_END);

        leftPanel = new GamePanel(game);
        leftPanel.draw();
        fGame.add(leftPanel);

        fGame.setVisible(true);
        fGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * megjeleníti a virológus statjait, amik a játék szemponjából fontosak lehetnek számára
     * @return
     */
    public JPanel initStats(){
        JPanel virologistout = new JPanel(new GridLayout(0,2));
        virologistout.add(new JLabel("Code count: "));
        virologistout.add(new JLabel(String.valueOf(game.getMap().getVirologists().get(game.getActive()).getCodeCount())));
        virologistout.add(new JLabel("Agent resistance: "));
        virologistout.add(new JLabel(String.valueOf(game.getMap().getVirologists().get(game.getActive()).getAgentResistance())));
        virologistout.add(new JLabel("Throwback available: "));
        virologistout.add(new JLabel(String.valueOf(game.getMap().getVirologists().get(game.getActive()).isThrowBackAvailable())));
        virologistout.add(new JLabel("Effects count:"));
        virologistout.add(new JLabel(String.valueOf(game.getMap().getVirologists().get(game.getActive()).getEffects().size())));
        virologistout.add(new JLabel("Bag size: "));
        virologistout.add(new JLabel(String.valueOf(game.getMap().getVirologists().get(game.getActive()).getBag().getSize()) +
                " / " + String.valueOf(game.getMap().getVirologists().get(game.getActive()).getBag().getUsedSize())));
        virologistout.add(new JLabel("Untouchable: "));
        virologistout.add(new JLabel(String.valueOf(game.getMap().getVirologists().get(game.getActive()).getUntouchable())));
        return virologistout;
    }

    /**
     * Frissíti a virológus statjait
     */
    public void updateStats(){
        pVirologistStats.remove(virologistout);
        virologistout = initStats();
        pVirologistStats.add(virologistout);
        pVirologistStats.revalidate();
        pVirologistStats.repaint();
    }

    /**
     * Begyűjt valamit a virológus attól függően, hogy hol áll
     */
    private void collect(){
        if(game.getMap().getVirologists().get(game.getActive()).getTile() instanceof Laboratory){
            game.getMap().getVirologists().get(game.getActive()).PalpateWall();
            updateStats();
            if(game.getMap().getMapNumber() == 1 && game.getMap().getVirologists().get(game.getActive()).getCodeCount() == 2) {
                game.endGame();
                JFrame jFrame = new JFrame();
                Object[] options = {"New Game!", "Exit!"};
                int result = jPopup.showOptionDialog(jFrame, " Congratulations! You're the winner! \n What would you like to do?", "Game Ended", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if(result == 0){
                    MainMenu mainMenu = new MainMenu(game, this);
                }
                else if(result == 1){
                    System.exit(0);
                }
            }
            else if(game.getMap().getMapNumber() == 2 && game.getMap().getVirologists().get(game.getActive()).getCodeCount() == 3) {
                game.endGame();
                game.endGame();
                JFrame jFrame = new JFrame();
                Object[] options = {"New Game!", "Exit!"};
                int result = jPopup.showOptionDialog(jFrame, " Congratulations! You're the winner! \n What would you like to do?", "Game Ended", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if(result == 0){
                    MainMenu mainMenu = new MainMenu(game, this);
                }
                else if(result == 1){
                    System.exit(0);
                }
            }
        }
        if(game.getMap().getVirologists().get(game.getActive()).getTile() instanceof Storage){
            game.getMap().getVirologists().get(game.getActive()).CollectMaterial();
            updateStats();
        }
        if(game.getMap().getVirologists().get(game.getActive()).getTile() instanceof Shelter){
            game.getMap().getVirologists().get(game.getActive()).CollectProtectiveGear();
            updateStats();
        }
    }

    /**
     * Visszaadja a játékot megjelenítő panelt
     * @return
     */
    public GamePanel getGamePanel(){
        return leftPanel;
    }

    /**
     * A gombok lenyomását figyeli és ha valamelyiket lenyomják, akkor végrahajtja az utasításait
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("newgame"))
        {
            MainMenu mainMenu = new MainMenu(game, this);
        }
        if(game.isGamerunning()) {
            if (e.getActionCommand().equals("bag")) {
                BagMenu Bagmenu = new BagMenu(game.getMap().getVirologists().get(game.getActive()), this);
            }
            if (e.getActionCommand().equals("codes")) {
                GeneticCodesMenu GCmenu = new GeneticCodesMenu(game.getMap().getVirologists().get(game.getActive()), this);
            }
            if (e.getActionCommand().equals("collect")) {
                if (game.getMap().getVirologists().get(game.getActive()).getTile().GetOtherVirologist(game.getMap().getVirologists().get(game.getActive())) == null) {
                    collect();
                } else {
                    Virologist virologist = game.getMap().getVirologists().get(game.getActive());
                    Virologist otherVirologist = game.getMap().getVirologists().get(game.getActive()).getTile().GetOtherVirologist(virologist);
                    boolean paralyzed = false;
                    if (otherVirologist.getEffects().size() > 0) {
                        for (Effects effect : otherVirologist.getEffects()) {
                            if (effect instanceof Paralyzed) {
                                paralyzed = true;
                                break;
                            }
                        }
                    }
                    if (paralyzed) {
                        JFrame jFrame = new JFrame();
                        Object[] options = {"Collect!", "Take gear!"};
                        int result = jPopup.showOptionDialog(jFrame, "What would you like to do with the protective gear?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (result == 0) {
                            collect();
                        } else if (result == 1) {
                            TakeGearMenu takeGearMenu = new TakeGearMenu(otherVirologist, this);
                        }
                    } else {
                        collect();
                    }
                }

            }
            if (e.getActionCommand().equals("wear")) {
                WearMenu Wearmenu = new WearMenu(game.getMap().getVirologists().get(game.getActive()), this);
            }
            if (e.getActionCommand().equals("endturn")) {
                game.setActive();
                updateStats();
                Virologist v = game.getMap().getVirologists().get(game.getActive());
                if (v.getBearDance()) {
                    int viroCount = 0;
                    while (v.getBearDance()){
                        v.BearDanceActionPerform();
                        game.setActive();
                        updateStats();
                        v = game.getMap().getVirologists().get(game.getActive());
                        viroCount++;
                        if(viroCount == game.getMap().getVirologists().size()){
                            game.endGame();
                            JFrame jFrame = new JFrame();
                            Object[] options = {"New Game!", "Exit!"};
                            int result = jPopup.showOptionDialog(jFrame, " Every virologist got infected with BearDance virus!", "Game Ended", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                            if(result == 0){
                                MainMenu mainMenu = new MainMenu(game, this);
                            }
                            else if(result == 1){
                                System.exit(0);
                            }
                            break;
                        }
                    }

                } else if (v.getVitusDance()) {
                    int viroCount = 0;
                    while(v.getVitusDance()){
                        v.VitusDanceActionPerform();
                        game.setActive();
                        updateStats();
                        viroCount++;
                        if(viroCount == game.getMap().getVirologists().size()){
                            break;
                        }
                    }

                }
                timeStep(v);
                game.setHasMoved(false);
                leftPanel.draw();
            }
        }
    }
    /*
     * Lépteti a virológus idejét
     * */
    public void timeStep(Virologist v) {
        int iter = 0;
        while (v.getBag().getAgents() != null  && iter < v.getBag().getAgents().size()) {
            v.getBag().getAgents().get(iter).Step();
            iter++;
        }
        int iter1 = 0;
        while(v.getEffects() != null && iter1 < v.getEffects().size()) {
            v.getEffects().get(iter1).Step();
            iter1++;
        }
    }
}
