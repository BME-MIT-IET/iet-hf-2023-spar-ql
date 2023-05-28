package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class BagTest {

    private Bag bag;

    @BeforeEach
    public void setUp(){
        bag = new Bag();
    }

    @Test
    public void Test_SetBonusSize(){
        bag.setBonusSize(1);

        Assertions.assertEquals(bag.getSize(),31);
    }

    @Test
    public void Test_UsedSize(){
        int expectedUsedSize = bag.getMaterials().size()+bag.getProtectiveGears().size()+bag.getAgents().size();
        Assertions.assertEquals(expectedUsedSize,bag.getUsedSize());
    }

    @Test
    public void Test_Discard_Agent(){
        Agent aMock = mock(Agent.class);
        bag.Add(aMock);

        bag.Discard(aMock);

        Assertions.assertEquals(0,bag.getAgents().size());
    }

    @Test
    public void Test_Discard_ProtectiveGear_WhenProtectiveGearIsNotWorn(){
        Virologist vMock= mock(Virologist.class);
        ProtectiveGear pgMock = mock(ProtectiveGear.class);
        ArrayList<ProtectiveGear> wear = new ArrayList<ProtectiveGear>();

        bag.setVirologist(vMock);
        bag.Add(pgMock);
        when(vMock.getWear()).thenReturn(wear);

        bag.Discard(pgMock);

        Assertions.assertEquals(0,bag.getProtectiveGears().size());
    }

    @Test
    public void Test_Discard_ProtectiveGear_WhenProtectiveGearIsWorn(){
        Virologist vMock= mock(Virologist.class);
        ProtectiveGear pgMock = mock(ProtectiveGear.class);
        ArrayList<ProtectiveGear> wear = new ArrayList<ProtectiveGear>();
        bag.setVirologist(vMock);

        bag.Add(pgMock);
        wear.add(pgMock);
        when(vMock.getWear()).thenReturn(wear);
        doNothing().when(pgMock).takeAway(any(Virologist.class));


        bag.Discard(pgMock);

        Assertions.assertEquals(0,bag.getProtectiveGears().size());
        verify(pgMock).takeAway(any(Virologist.class));
    }

    @Test
    public void Test_Add_Agent(){
        Agent aMock = mock(Agent.class);

        bag.Add(aMock);

        Assertions.assertEquals(1,bag.getAgents().size());
        verify(aMock).setBag(bag);
    }

    @Test
    public void Test_Add_ProtectiveGear(){
        ProtectiveGear pgMock = mock(ProtectiveGear.class);

        bag.Add(pgMock);

        Assertions.assertEquals(1,bag.getProtectiveGears().size());
    }

    @Test
    public void Test_Add_Material(){
        Material mMock = mock(Material.class);

        bag.Add(mMock);

        Assertions.assertEquals(1,bag.getMaterials().size());
    }

}
