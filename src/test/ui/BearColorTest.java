package ui;

import Control.Game;
import Control.GameMenu;
import Control.MainMenu;
import Model.*;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.swing.data.TableCell.row;
import static org.junit.Assert.*;

public class BearColorTest {
    private FrameFixture window;
    private Robot robot;
    private GameMenu gameMenu;
    private MainMenu mainMenu;
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        mainMenu = new MainMenu(game);

        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        window = new FrameFixture(robot, mainMenu.getFrame());

        window.button("bStartGame").click();

        gameMenu = mainMenu.getGameMenu();
        window = new FrameFixture(robot, mainMenu.getGameMenuFrame());

        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testBearColor(){
        Virologist target = game.getMap().getVirologists().get(game.getActive());
        Tile tile = target.getTile();

        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        BearDanceAgent agent = new BearDanceAgent(materials,"beardance");

        window.button("bEndTurn").click();

        Virologist active = game.getMap().getVirologists().get(game.getActive());
        Bag bag = new Bag();
        bag.Add(agent);
        active.setBag(bag);
        active.setTile(tile);

        assertNotEquals(active, target);

        window.button("bBag").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);
        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        window.panel("pAgent").table("agentTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Use on other virologist!").click();
        assertTrue(target.getEffects().size() == 1);

        window = new FrameFixture(robot, mainMenu.getGameMenuFrame());
        window.button("bEndTurn").click();

        assertEquals(Color.BLACK,gameMenu.getLeftPanel().getVirologistViews().get(target.getId()).getColor());

    }
}
