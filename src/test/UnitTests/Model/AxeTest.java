package UnitTests.Model;
import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class AxeTest {

    private Axe axe;

    @BeforeEach
    public void setUp(){
        axe = new Axe("Test Axe");
    }

    @Test
    public void Test_TakeAway(){
        Virologist vMock = mock(Virologist.class);
        Bag bag = new Bag();
        bag.Add(axe);
        bag.setVirologist(vMock);
        when(vMock.getBag()).thenReturn(bag);
        vMock.Wear(axe);
        
        axe.takeAway(vMock);

        Assertions.assertEquals(vMock.getBag().getProtectiveGears().size(),0);
        Assertions.assertEquals(vMock.getWear().size(),0);
    }

    @Test
    public void Test_Attack(){
        Virologist victim = Mockito.mock(Virologist.class);

        axe.Attack(victim);

        verify(victim).Die();
        Assertions.assertFalse(axe.getUsable());
    }

    @Test
    public void Test_Use(){
        Virologist vMock = mock(Virologist.class);
        Agent aMock = mock(Agent.class);
        Axe axeSpy = spy(axe);
        doNothing().when(axeSpy).Attack(any(Virologist.class));

        axeSpy.Use(vMock,aMock);

        verify(axeSpy).Attack(any(Virologist.class));
    }

    @Test
    public void Test_Destroy(){
        Virologist vMock = mock(Virologist.class);
        Axe axeSpy = spy(axe);
        axeSpy.setAttribute(vMock);
        doNothing().when(axeSpy).takeAway(any(Virologist.class));

        axeSpy.Destroy();

        verify(axeSpy).takeAway(any(Virologist.class));
    }
}
