package NonFunctionalTests.ReliabilityTests;

import Control.Game;
import Control.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sonatype.guice.bean.containers.Main;

import javax.swing.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoadTest {

    private class StartThread extends Thread {
        boolean exit = true;

        public void setExit( ) {
            this.exit = true;
        }

        @Override
        public void run() {
            Game game = new Game();
            MainMenu mainMenu = new MainMenu(game);

            while(!exit){

            }
        }
    }
    public void setUpTestEscape(StartThread startThread) throws InterruptedException {
        Thread.sleep(1000);
        startThread.setExit();
    }

    @BeforeEach
    void SetUp() {
        StartThread firstStart  = new StartThread();
        StartThread secondStart  = new StartThread();
        firstStart.start();
        secondStart.start();

        try {
            setUpTestEscape(firstStart);
            setUpTestEscape(secondStart);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void main() {

    }
}