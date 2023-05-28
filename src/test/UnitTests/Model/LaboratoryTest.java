package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class LaboratoryTest {

    private Laboratory laboratory;
    private int[] pointsX = {0,0};
    private int[] pointsY = {0,0};

    private GeneticCode gcMock;

    @BeforeEach
    public void setup(){
        gcMock = mock(GeneticCode.class);
    }

    @Test
    public void Test_Accept_WhenBearDanceNotNull(){
        BearDanceAgent bdaMock = mock(BearDanceAgent.class);
        laboratory = new Laboratory(gcMock,bdaMock,pointsX,pointsY ,1);
        Virologist vMock = mock(Virologist.class);

        Laboratory spyLaboratory = spy(laboratory);

        spyLaboratory.Accept(vMock);

        verify(spyLaboratory).LaboratoryInfect(vMock);
    }
    @Test

    public void Test_GetCollectable(){
        BearDanceAgent bdaMock = mock(BearDanceAgent.class);
        laboratory = new Laboratory(gcMock,bdaMock,pointsX,pointsY ,1);
        Laboratory spyLaboratory = spy(laboratory);

        GeneticCode gc = spyLaboratory.GetCollectable();

        Assertions.assertEquals(gcMock,gc);
        verify(spyLaboratory).Palpate();
    }


    @Test
    public void Test_LaboratoryInfect_WhenVirologistIsNotUntouchableAndRandomIsGreater(){
        Virologist vMock = mock(Virologist.class);
        laboratory = new Laboratory(gcMock,pointsX,pointsY ,1);

        when(vMock.getAgentResistance()).thenReturn(0.0);
        when(vMock.getUntouchable()).thenReturn(false);

        laboratory.LaboratoryInfect(vMock);

        verify(vMock).HitByAgent(any(BearDanceAgent.class));

    }

    @Test
    public void Test_IsInfects_WhenBearDanceIsNull(){
        laboratory = new Laboratory(gcMock,pointsX,pointsY ,1);

        boolean result = laboratory.isInfects();

        Assertions.assertFalse(result);
    }

    @Test
    public void Test_IsInfects_WhenBearDanceIsNotNull(){
        BearDanceAgent bdaMock = mock(BearDanceAgent.class);
        laboratory = new Laboratory(gcMock,bdaMock,pointsX,pointsY ,1);

        boolean result = laboratory.isInfects();

        Assertions.assertTrue(result);
    }


}
