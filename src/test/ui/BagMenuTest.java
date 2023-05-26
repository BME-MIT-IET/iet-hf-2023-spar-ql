package ui;

import Control.Game;
import Control.GameMenu;
import Control.MainMenu;
import Model.*;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.core.Robot;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Timeout;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.*;
import java.util.ArrayList;

import static org.assertj.swing.data.TableCell.row;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BagMenuTest {
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
    public void test1_BagDropItems() {
        //Drop protectiveGear
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.getBag().Add(new Glove("Glove"));
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 1);
        window.button("bBag").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        window.panel("pProtectiveGears").table("protectiveGearTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Discard!").click();
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 0);

        //Drop material
        activeVirologist.getBag().Add(new Material("TDP"));
        assertEquals(activeVirologist.getBag().getMaterials().size(), 1);
        window = new FrameFixture(robot, gameMenu.getFrame());
        window.button("bBag").click();

        visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        window.panel("pMaterials").table("materialTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Discard!").click();
        assertEquals(activeVirologist.getBag().getMaterials().size(), 0);

        //Drop agent
        ArrayList<Material> agent2materials = new ArrayList<>();
        agent2materials.add(new Material("dUTP"));
        agent2materials.add(new Material("dUTP"));
        agent2materials.add(new Material("CDP"));
        agent2materials.add(new Material("CDP"));
        agent2materials.add(new Material("Szerin"));
        agent2materials.add(new Material("Szerin"));
        agent2materials.add(new Material("Szerin"));
        UntouchableAgent untouchableAgent = new UntouchableAgent(agent2materials, "Untouchable agent");

        activeVirologist.getBag().Add(untouchableAgent);
        assertEquals(activeVirologist.getBag().getAgents().size(), 1);
        window = new FrameFixture(robot, gameMenu.getFrame());

        window.button("bBag").click();

        visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        window.panel("pAgent").table("agentTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Discard!").click();
        assertEquals(activeVirologist.getBag().getAgents().size(), 0);

    }

    @Test
    public void test2_BagWearItems() {
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.getBag().Add(new Glove("Glove"));
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 1);
        assertEquals(activeVirologist.getWear().size(), 0);

        window.button("bBag").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getBagmenu().getFrame());
        window.panel("pProtectiveGears").table("protectiveGearTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Wear!").click();
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 1);
        assertEquals(activeVirologist.getWear().size(), 1);
    }

}
