package NonFunctionalTests.ReliabilityTests;

import Control.Game;
import Control.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class LoadTest {


    private class StartThread extends Thread {
        volatile boolean exit = false;

        public void setExit( ) {
            this.exit = true;
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

    ArrayList<StartThread> threadArray;
    @BeforeEach
    void SetUp() {
        threadArray = new ArrayList<>();
    }


    @ParameterizedTest
    @ValueSource(ints = {10, 100})
    void main( int numberOfThreads){
        for(int i = 0; i < 100; i++) {
            threadArray.add(new StartThread());
        }

        for (StartThread t : threadArray) {
            t.start();
            try {
                setUpTestEscape(t, 1800 );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            for (StartThread t : threadArray) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}