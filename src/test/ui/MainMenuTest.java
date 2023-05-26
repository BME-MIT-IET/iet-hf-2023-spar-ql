package test.ui;

import Control.Game;
import Control.MainMenu;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.core.Robot;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.fixture.JRadioButtonFixture;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainMenuTest {
    private FrameFixture window;
    private Robot robot;

    private MainMenu mainMenu;

    @Before
    public void setUp() {
        Game game = new Game();
        mainMenu = new MainMenu(game);

        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        window = new FrameFixture(robot, mainMenu.getFrame());
        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void test1_Init() {
        boolean rMap1Selected = window.radioButton("rMap1").requireSelected().target().isSelected();
        boolean rMap2Selected = window.radioButton("rMap2").requireNotSelected().target().isSelected();
        boolean rPlayers4Selected = window.radioButton("rPlayers4").requireSelected().target().isSelected();
        boolean rPlayers5Selected = window.radioButton("rPlayers5").requireNotSelected().target().isSelected();
        boolean rPlayers6Selected = window.radioButton("rPlayers6").requireNotSelected().target().isSelected();

        assertTrue(rMap1Selected);
        assertFalse(rMap2Selected);

        assertTrue(rPlayers4Selected);
        assertFalse(rPlayers5Selected);
        assertFalse(rPlayers6Selected);
    }

    @Test
    public void test2_Change() {
        JRadioButtonFixture rMap1 = window.radioButton("rMap1");
        JRadioButtonFixture rMap2 = window.radioButton("rMap2");
        JRadioButtonFixture rPlayers4 = window.radioButton("rPlayers4");
        JRadioButtonFixture rPlayers5 = window.radioButton("rPlayers5");
        JRadioButtonFixture rPlayers6 = window.radioButton("rPlayers6");

        rMap2.click();
        rPlayers6.click();

        boolean rMap1Selected = rMap1.requireNotSelected().target().isSelected();
        boolean rMap2Selected = rMap2.requireSelected().target().isSelected();
        boolean rPlayers4Selected = rPlayers4.requireNotSelected().target().isSelected();
        boolean rPlayers5Selected = rPlayers5.requireNotSelected().target().isSelected();
        boolean rPlayers6Selected = rPlayers6.requireSelected().target().isSelected();

        assertFalse(rMap1Selected);
        assertTrue(rMap2Selected);

        assertFalse(rPlayers4Selected);
        assertFalse(rPlayers5Selected);
        assertTrue(rPlayers6Selected);
    }

    @Test
    public void test3_Start() {
        window.button("bStartGame").click();

        boolean visible = window.target().isVisible();
        assertFalse(visible);

        window = new FrameFixture(robot, mainMenu.getGameMenuFrame());
        String expectedTitle = "VakVirologusok";
        String actualTitle = window.target().getTitle();
        assertEquals(actualTitle, expectedTitle);
    }
}
