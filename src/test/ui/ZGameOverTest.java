package ui;

import Control.Game;
import Control.GameMenu;
import Control.MainMenu;
import Model.*;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZGameOverTest {
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
    public void gameOver1NewGame(){
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.setCodeCount(1);
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        VitusDanceAgent agent = new VitusDanceAgent(materials, "vitusDance");
        GeneticCode code = new GeneticCode(agent);
        int[] pointsX = {0, 0};
        int[] pointsY = {0, 0};
        Laboratory lab = new Laboratory(code, pointsX, pointsY, 0);
        activeVirologist.setTile(lab);
        window.button("bCollect").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getEndgameFrame());
        window.optionPane().buttonWithText("New Game!").click();


        window = new FrameFixture(robot,gameMenu.getMainMenu().getFrame());
        visible = window.target().isVisible();
        assertTrue(visible);

    }

    /*
    * System.exit(0) terminates the whole program, therefore
    * if the test is "terminated" it should be considered as "passed"
    * */
    @Test
    public void gameOver2Exit() {
        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.setCodeCount(1);
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        VitusDanceAgent agent = new VitusDanceAgent(materials, "vitusDance");
        GeneticCode code = new GeneticCode(agent);
        int[] pointsX = {0, 0};
        int[] pointsY = {0, 0};
        Laboratory lab = new Laboratory(code, pointsX, pointsY, 0);
        activeVirologist.setTile(lab);
        window.button("bCollect").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getEndgameFrame());
        window.optionPane().buttonWithText("Exit!").click();
    }
}
