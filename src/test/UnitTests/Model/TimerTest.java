package UnitTests.Model;

import Model.Steppable;
import Model.Timer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class TimerTest {

    @Test
    public void Test_Tick(){
        Timer timer = new Timer();

        try {

            Field fSteppable = Timer.class.getDeclaredField("stepable");

            fSteppable.setAccessible(true);

            Steppable sMock = mock(Steppable.class);
            fSteppable.set(timer,sMock);

            timer.Tick();

            verify(sMock).Step();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
