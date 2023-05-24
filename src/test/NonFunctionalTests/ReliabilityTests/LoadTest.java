package NonFunctionalTests.ReliabilityTests;

import Control.Game;
import Control.MainMenu;
import org.junit.jupiter.api.Test;
import org.sonatype.guice.bean.containers.Main;

import static org.junit.jupiter.api.Assertions.*;

class LoadTest {

    private class startThread extends Thread {
        @Override
        public void run() {
            Game game = new Game();
            MainMenu mainMenu = new MainMenu(game);
        }
    }


    @Test
    void main() {
        Game game = new Game();
        MainMenu mainMenu = new MainMenu(game);

        //Thread firstStart  = new startThread();
        //Thread secondStart  = new startThread();
        //firstStart.start();
        // secondStart.start();

    }
}