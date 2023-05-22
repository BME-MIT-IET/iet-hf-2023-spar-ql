package test.ui;

import Control.BagMenu;
import Control.Game;
import Control.GameMenu;
import Control.MainMenu;
import Model.Virologist;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.*;

import static org.junit.Assert.*;

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
        window.button("bGeneticCodes").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getGeneticCodesMenu().getFrame());
        String expectedTitle = "Genteic Codes";
        String actualTitle = window.target().getTitle();
        assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void test4_Collect(){

    }
    @Test
    public void test5_Wear(){
        window.button("bWear").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getWearMenu().getFrame());
        String expectedTitle = "Wear";
        String actualTitle = window.target().getTitle();
        assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void test6_EndTurn(){
        Virologist virologist1 = gameMenu.getVirologist();
        int v1Number = game.getActive();

        assertEquals(Color.RED, gameMenu.getLeftPanel().getVirologistViews().get(v1Number).getColor());

        assertEquals(window.label("lCodeCount").text(), Long.toString(virologist1.getCodeCount()));
        assertEquals(window.label("lAgentResistance").text(), Double.toString(virologist1.getAgentResistance()));
        assertEquals(window.label("lThrowbackAvailable").text(), Boolean.toString(virologist1.isThrowBackAvailable()));
        assertEquals(window.label("lEffectsCount").text(), Integer.toString(virologist1.getEffects().size()));
        assertEquals(window.label("lBagSize").text(), virologist1.getBag().getSize() +
                " / " + virologist1.getBag().getUsedSize());
        assertEquals(window.label("lUntouchable").text(), Boolean.toString(virologist1.getUntouchable()));

        window.button("bEndTurn").click();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Virologist virologist2 = gameMenu.getVirologist();
        int v2Number = game.getActive();

        assertNotEquals(virologist1, virologist2);
        assertEquals(Color.BLUE, gameMenu.getLeftPanel().getVirologistViews().get(v1Number).getColor());
        assertEquals(Color.RED, gameMenu.getLeftPanel().getVirologistViews().get(v2Number).getColor());

        assertEquals(window.label("lCodeCount").text(), Long.toString(virologist2.getCodeCount()));
        assertEquals(window.label("lAgentResistance").text(), Double.toString(virologist2.getAgentResistance()));
        assertEquals(window.label("lThrowbackAvailable").text(), Boolean.toString(virologist2.isThrowBackAvailable()));
        assertEquals(window.label("lEffectsCount").text(), Integer.toString(virologist2.getEffects().size()));
        assertEquals(window.label("lBagSize").text(), virologist2.getBag().getSize() + " / " + virologist2.getBag().getUsedSize());
        assertEquals(window.label("lUntouchable").text(), Boolean.toString(virologist2.getUntouchable()));
    }
    @Test
    public void test7_Move(){

    }
}
