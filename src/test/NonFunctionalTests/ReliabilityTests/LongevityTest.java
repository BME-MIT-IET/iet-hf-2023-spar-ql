package test.ui;


import Control.Game;
import Control.GameMenu;
import Control.MainMenu;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.awt.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameMenuTest {
    private FrameFixture window;
    private Robot robot;
    private GameMenu gameMenu;

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        MainMenu mainMenu = new MainMenu(game);

        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        window = new FrameFixture(robot, mainMenu.getFrame());

        window.button("bStartGame").click();

        gameMenu = mainMenu.getGameMenu();
        window = new FrameFixture(robot, mainMenu.getGameMenuFrame());

        window.show();
    }
