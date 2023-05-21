package test.ui;

import Control.BagMenu;
import Control.Game;
import Control.GameMenu;
import Control.MainMenu;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameMenuTest {
    private FrameFixture window;
    private Robot robot;
    private GameMenu gameMenu;

    @Before
    public void setUp() {
        Game game = new Game();
        MainMenu mainMenu = new MainMenu(game);

        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        window = new FrameFixture(robot, mainMenu.getFrame());

        window.button("bStartGame").click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gameMenu = mainMenu.getGameMenu();
        window = new FrameFixture(robot, mainMenu.getGameMenuFrame());

        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void test1_NewGame(){
        window.menuItemWithPath("Game").click();

        window.menuItemWithPath("Game", "New Game").click();
        MainMenu mainMenu = gameMenu.getMainMenu();

        window = new FrameFixture(robot, mainMenu.getFrame());
        String expectedTitle = "New Game";
        String actualTitle = window.target().getTitle();
        assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void test2_Bag(){
        window.button("bBag").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        String expectedTitle = "Bag";
        String actualTitle = window.target().getTitle();
        assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void test3_GeneticCodes(){

    }
    @Test
    public void test4_Collect(){

    }
    @Test
    public void test5_Wear(){

    }
    @Test
    public void test6_EndTurn(){

    }
    @Test
    public void test7_Move(){

    }
}
