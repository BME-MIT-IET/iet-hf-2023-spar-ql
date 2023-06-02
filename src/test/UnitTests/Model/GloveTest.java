package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class GloveTest {

    private Glove glove;

    @BeforeEach
    public void setup(){
        glove = new Glove("Test Glove");
    }

    @Test
    public void Test_TakeAway(){
        Virologist vMock = mock(Virologist.class);

        glove.takeAway(vMock);

        verify(vMock).setThrowBackAvailable(false);
        verify(vMock).Unwear(glove);
    }

    @Test
    public void Test_ThrowBack_WhenNotUntouchable(){
        Virologist vMock = mock(Virologist.class);
        Agent aMock = mock(Agent.class);
        when(vMock.getUntouchable()).thenReturn(true);

        glove.throwBack(vMock,aMock);

        Assertions.assertEquals(2,glove.getRemainingUses());
    }

    @Test
    public void Test_ThrowBack_WhenUntouchable(){
        Virologist vMock = mock(Virologist.class);
        Agent aMock = mock(Agent.class);
        when(vMock.getUntouchable()).thenReturn(false);

        glove.throwBack(vMock,aMock);

        verify(vMock).HitByAgent(aMock);
        Assertions.assertEquals(2,glove.getRemainingUses());
    }

    @Test
    public void Test_SetAttribute(){
        Virologist vMock = mock(Virologist.class);

        glove.setAttribute(vMock);

        verify(vMock).setThrowBackAvailable(true);
    }

    @Test
    public void Test_Wear_WhenIsNotWorn(){
        Virologist vMock = mock(Virologist.class);
        Glove spyGlove = spy(glove);

        spyGlove.setVirologist(vMock);

        spyGlove.Wear();

        verify(spyGlove).setAttribute(vMock);
    }

    @Test
    public void Test_Use_WhenRemainingUsesGreaterThanZero(){
        Virologist vMock = mock(Virologist.class);
        Agent aMock = mock(Agent.class);
        Glove spyGlove = spy(glove);

        spyGlove.Use(vMock,aMock);

        verify(spyGlove).throwBack(vMock,aMock);
    }

    @Test
    public void Test_Use_WhenRemainingUsesBecomesZero(){
        Virologist vMock = spy(new Virologist());
        Agent aMock = mock(Agent.class);
        Bag bMock = mock(Bag.class);
        Glove spyGlove = spy(glove);
        spyGlove.setVirologist(vMock);
        ArrayList<ProtectiveGear> wear = new ArrayList<ProtectiveGear>();
        wear.add(spyGlove);

        try {
            Field fRemainingUses = Glove.class.getDeclaredField("remainingUses");

            fRemainingUses.setAccessible(true);

            fRemainingUses.set(spyGlove,1);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        when(vMock.getWear()).thenReturn(wear);
        when(vMock.getBag()).thenReturn(bMock);
        when(vMock.getUntouchable()).thenReturn(true);

        spyGlove.Use(vMock,aMock);

        Assertions.assertEquals(0,spyGlove.getRemainingUses());
        verify(vMock).setThrowBackAvailable(false);
        verify(vMock).getBag();
        verify(bMock).Discard(spyGlove);
        verify(vMock).getWear();
        Assertions.assertFalse(wear.contains(spyGlove));
    }
}
