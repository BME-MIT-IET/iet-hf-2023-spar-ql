package NonFunctionalTests.ReliabilityTests;

import Control.Game;
import Control.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sonatype.guice.bean.containers.Main;

import javax.swing.*;

import java.awt.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoadTest {

    Thread checker = new Thread() {
        volatile boolean escape = false;
        @Override
        public void run() {

            while (!escape) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    setUpTestEscape(firstStart, 2000);
                    setUpTestEscape(secondStart, 2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                escape = true;
            }
        }
    };

    private class StartThread extends Thread {
        volatile boolean exit = false;

        public void setExit( ) {
            this.exit = true;
        }

        MainMenu mainMenu;

        public MainMenu getMainMenu () {
            return mainMenu ;
        }
        @Override
        public void run() {
            Game game = new Game();
            MainMenu mainMenu = new MainMenu(game);
            System.out.println("Created");
            while(!exit){

            }
            System.out.println("Closed");
        }
    }
    public void setUpTestEscape(StartThread startThread, int runTime) throws InterruptedException {
        Thread.sleep(runTime);
        startThread.setExit();
    }

    StartThread firstStart;
    StartThread secondStart;
    @BeforeEach
    void SetUp() {
        firstStart  = new StartThread();
        secondStart  = new StartThread();
    }

    @Test
    void main() throws InterruptedException {
        firstStart.start();
        secondStart.start();
        checker.start();
        System.out.println("Checked");

        try {
            firstStart.join();
            firstStart.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}