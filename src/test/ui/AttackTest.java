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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.assertj.swing.data.TableCell.row;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AttackTest {
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


    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testAttack(){
        Virologist target = game.getMap().getVirologists().get(game.getActive());
        Tile targetTile = target.getTile();

        window.button("bEndTurn").click();
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        activeVirologist.getBag().Add(new VitusDanceAgent(materials,"vitusdance"));
        assertTrue(activeVirologist.getBag().getUsedSize() == 1);
        activeVirologist.setTile(targetTile);
        assertNotEquals(activeVirologist, target);

        window.button("bBag").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);
        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        window.panel("pAgent").table("agentTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Use on other virologist!").click();
        assertTrue(target.getEffects().size() == 1);
    }
}
