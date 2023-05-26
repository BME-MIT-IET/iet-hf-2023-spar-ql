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
public class WearMenuTest {
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
    public void test1_WearMenuUnWearItems() {
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.getBag().Add(new Glove("Glove"));
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 1);

        activeVirologist.Wear(activeVirologist.getBag().getProtectiveGears().get(0));
        assertEquals(activeVirologist.getWear().size(), 1);
        window.button("bWear").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getWearMenu().getFrame());
        window.panel("pProtectiveGears").table("protectiveGearTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Unwear!").click();
        assertEquals(activeVirologist.getWear().size(), 0);
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 1);
    }


    @Test
    public void test2_WearMenuDiscardItems() {
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.getBag().Add(new Glove("Glove"));
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 1);

        activeVirologist.Wear(activeVirologist.getBag().getProtectiveGears().get(0));
        assertEquals(activeVirologist.getWear().size(), 1);
        window.button("bWear").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getWearMenu().getFrame());
        window.panel("pProtectiveGears").table("protectiveGearTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Discard!").click();
        assertEquals(activeVirologist.getWear().size(), 0);
        assertEquals(activeVirologist.getBag().getProtectiveGears().size(), 0);

    }

}
