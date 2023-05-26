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
public class GeneticCodesMenuTest {
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
    public void test1_MakeAgentFromGeneticCode() {

        ArrayList<Material> agent4materials = new ArrayList<>();
        agent4materials.add(new Material("TDP"));
        agent4materials.add(new Material("TDP"));
        agent4materials.add(new Material("TDP"));
        agent4materials.add(new Material("Lizin"));
        agent4materials.add(new Material("Lizin"));
        agent4materials.add(new Material("CDP"));
        ParalyzeAgent paralyzeAgent = new ParalyzeAgent(agent4materials, "Paralyze agent");
        GeneticCode geneticCode4 = new GeneticCode(paralyzeAgent);

        Virologist activeVirologist = game.getMap().getVirologists().get(game.getActive());
        activeVirologist.getBag().getMaterials().addAll(agent4materials);
        activeVirologist.LearnCode(geneticCode4);

        assertEquals(activeVirologist.getBag().getMaterials().size(), 6);
        assertEquals(activeVirologist.getCodeCount(), 1);

        window.button("bGeneticCodes").click();

        boolean visible = window.target().isVisible();
        assertTrue(visible);

        window = new FrameFixture(robot, gameMenu.getGeneticCodesMenu().getFrame());
        window.panel("pCodes").table("geneticCodeTable").cell(row(0).column(0)).click();
        window.optionPane().buttonWithText("Create Agent!").click();
        assertEquals(activeVirologist.getBag().getAgents().size(), 1);
        assertEquals(activeVirologist.getCodeCount(), 1);
        assertEquals(activeVirologist.getBag().getMaterials().size() , 0);
    }
}
