package UnitTests.Model;

import Model.Tile;
import Model.Virologist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class TileTest {

    private Tile tile;
    private int[] pointsX = {0,0};
    private int[] pointsY = {0,0};

    @BeforeEach
    public void setup(){
        tile = new Tile(pointsX,pointsY,1);
    }

    @Test
    public void Test_Remove(){
        Virologist vMock = mock(Virologist.class);

        tile.Accept(vMock);

        tile.Remove(vMock);

        try {
            Field fVirologists = Tile.class.getDeclaredField("virologists");

            fVirologists.setAccessible(true);

            ArrayList<Virologist> fVirologistsValue = (ArrayList<Virologist>) fVirologists.get(tile);

            Assertions.assertFalse(fVirologistsValue.contains(vMock));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_Accept_WhenCapacityIsFull(){
        Virologist vMock1 = mock(Virologist.class);
        Virologist vMock2 = mock(Virologist.class);
        Virologist vMock3 = mock(Virologist.class);

        tile.Accept(vMock1);
        tile.Accept(vMock2);

        tile.Accept(vMock3);

        try {
            Field fVirologists = Tile.class.getDeclaredField("virologists");

            fVirologists.setAccessible(true);

            ArrayList<Virologist> fVirologistsValue = (ArrayList<Virologist>) fVirologists.get(tile);

            Assertions.assertFalse(fVirologistsValue.contains(vMock3));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_Accept_WhenCapacityIsNotFull(){
        Virologist vMock = mock(Virologist.class);

        tile.Accept(vMock);

        try {
            Field fVirologists = Tile.class.getDeclaredField("virologists");

            fVirologists.setAccessible(true);

            ArrayList<Virologist> fVirologistsValue = (ArrayList<Virologist>) fVirologists.get(tile);

            Assertions.assertTrue(fVirologistsValue.contains(vMock));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_GetNeighbor_WhenNIsInvalid(){
        Assertions.assertNull(tile.GetNeighbor(0));
    }

    @Test
    public void Test_GetNeighbor_WhenNIsValid(){
        Tile tile2 = mock(Tile.class);
        when(tile2.getId()).thenReturn(2);

        tile.setNeighbor(tile2);

        Tile neighbor = tile.GetNeighbor(2);

        Assertions.assertEquals(tile2,neighbor);
    }

    @Test
    public void Test_SetNeighbor(){
        Tile tile2 = mock(Tile.class);

        tile.setNeighbor(tile2);

        Assertions.assertTrue(tile.getAdjacentTiles().contains(tile2));
        verify(tile2).setTheOtherNeighbor(tile);
    }

    @Test
    public void Test_SetOtherNeighbor(){
        Tile tile2 = mock(Tile.class);

        tile.setTheOtherNeighbor(tile2);

        Assertions.assertTrue(tile.getAdjacentTiles().contains(tile2));
    }

    @Test
    public void Test_GetCollectable(){
        Assertions.assertNull(tile.GetCollectable());
    }

    @Test
    public void Test_GetOtherVirologist_WhenThereIsNoOtherVirologist(){
        Virologist vMock = mock(Virologist.class);

        tile.Accept(vMock);

        Assertions.assertNull(tile.GetOtherVirologist(vMock));
    }

    @Test
    public void Test_GetOtherVirologist_WhenThereIsOtherVirologist(){
        Virologist vMock1 = mock(Virologist.class);
        Virologist vMock2 = mock(Virologist.class);

        tile.Accept(vMock1);
        tile.Accept(vMock2);

        Assertions.assertEquals(vMock2,tile.GetOtherVirologist(vMock1));
    }
}
