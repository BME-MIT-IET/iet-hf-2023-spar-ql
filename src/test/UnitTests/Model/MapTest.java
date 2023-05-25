package UnitTests.Model;

import Control.Game;
import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MapTest {
    private Map map;
    private int[] pointsX = {0,0,0};
    private int[] pointsY = {0,0,0};

    @BeforeEach
    public void setup(){
        map = new Map();
    }

    @Test
    public void Test_Build_WhenMapNumberIsOne(){
        Virologist vMock = mock(Virologist.class);
        Map mapSpy = spy(map);

        mapSpy.Build();

        verify(mapSpy).createTiles1();
        Assertions.assertNotEquals(0,mapSpy.getVirologists().size());

        CheckVirologists(1, mapSpy);
    }

    private void CheckVirologists(int mapNumber, Map mapSpy){
        List<Virologist> virologists = mapSpy.getVirologists();

        for(Virologist item : virologists){
            try {
                Field fMap = Virologist.class.getDeclaredField("map");

                fMap.setAccessible(true);

                Map virologistMapValue = (Map) fMap.get(item);

                Assertions.assertEquals(mapNumber,virologistMapValue.getMapNumber());
                Assertions.assertNotNull(item.getBag());
                Assertions.assertNotNull(item.getTile());

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void Test_Build_WhenMapNumberIsTwo(){
        Map mapSpy = spy(map);
        mapSpy.setMapNumber(2);

        mapSpy.Build();

        verify(mapSpy).createTiles2();
        Assertions.assertNotEquals(0,mapSpy.getVirologists().size());

        CheckVirologists(2, mapSpy);
    }

    @Test
    public void Test_AddPolygon_WhenTypeIsTile(){
        map.addPolygon(1,pointsX,pointsY,1);

        Assertions.assertTrue(map.getTiles().get(map.getTiles().size()-1) instanceof Tile);
    }

    @Test
    public void Test_AddPolygon_WhenTypeIsLaboratory(){
        map.addPolygon(2,pointsX,pointsY,1);

        Assertions.assertTrue(map.getTiles().get(map.getTiles().size()-1) instanceof Laboratory);
    }

    @Test
    public void Test_AddPolygon_WhenTypeIsShelter(){
        map.addPolygon(3,pointsX,pointsY,1);

        Assertions.assertTrue(map.getTiles().get(map.getTiles().size()-1) instanceof Shelter);
    }

    @Test
    public void Test_AddPolygon_WhenTypeIsStorage(){
        map.addPolygon(4,pointsX,pointsY,1);

        Assertions.assertTrue(map.getTiles().get(map.getTiles().size()-1) instanceof Storage);
    }

    @Test
    public void Test_CreatePolygon_WithThreePoints(){
        int type = 1;
        Map mapSpy = spy(map);

        mapSpy.createPolygon(type,pointsX[0],pointsY[0],pointsX[1],pointsY[1],pointsX[2],pointsY[2]);

        verify(mapSpy).addPolygon(type,pointsX,pointsY,3);
    }

    @Test
    public void Test_CreatePolygon_WithFourPoints(){
        int type = 1;
        Map mapSpy = spy(map);

        pointsX = new int[]{0, 0, 0, 0};
        pointsY = new int[]{0, 0, 0, 0};

        mapSpy.createPolygon(type,pointsX[0],pointsY[0],pointsX[1],pointsY[1],pointsX[2],pointsY[2],pointsX[3],pointsY[3]);

        verify(mapSpy).addPolygon(type,pointsX,pointsY,4);
    }

    @Test
    public void Test_CreatePolygon_WithFivePoints(){
        int type = 1;
        Map mapSpy = spy(map);

        pointsX = new int[]{0, 0, 0, 0, 0};
        pointsY = new int[]{0, 0, 0, 0, 0};

        mapSpy.createPolygon(type,pointsX[0],pointsY[0],pointsX[1],pointsY[1],pointsX[2],pointsY[2],pointsX[3],pointsY[3],pointsX[4],pointsY[4]);

        verify(mapSpy).addPolygon(type,pointsX,pointsY,5);
    }

    @Test
    public void Test_CreatePolygon_WithSixPoints(){
        int type = 1;
        Map mapSpy = spy(map);

        pointsX = new int[]{0, 0, 0, 0, 0, 0};
        pointsY = new int[]{0, 0, 0, 0, 0, 0};

        mapSpy.createPolygon(type,pointsX[0],pointsY[0],pointsX[1],pointsY[1],pointsX[2],pointsY[2],pointsX[3],pointsY[3],pointsX[4],pointsY[4],pointsX[5],pointsY[5]);

        verify(mapSpy).addPolygon(type,pointsX,pointsY,6);
    }

    @Test
    public void Test_CreatePolygon_WithSevenPoints(){
        int type = 1;
        Map mapSpy = spy(map);

        pointsX = new int[]{0, 0, 0, 0, 0, 0, 0};
        pointsY = new int[]{0, 0, 0, 0, 0, 0, 0};

        mapSpy.createPolygon(type,pointsX[0],pointsY[0],pointsX[1],pointsY[1],pointsX[2],pointsY[2],pointsX[3],pointsY[3],pointsX[4],pointsY[4],pointsX[5],pointsY[5],pointsX[6],pointsY[6]);

        verify(mapSpy).addPolygon(type,pointsX,pointsY,7);
    }

    @Test
    public void Test_CreateTiles1(){
        int type = 1;
        Map mapSpy = spy(map);

        mapSpy.createTiles1();

        List<Tile> tiles = mapSpy.getTiles();

        for(int i=0; i<14;i++){
            Assertions.assertFalse(tiles.get(i) instanceof Laboratory);
            Assertions.assertFalse(tiles.get(i) instanceof Shelter);
            Assertions.assertFalse(tiles.get(i) instanceof Storage);
        }

        for(int i=14; i<16;i++){
            Assertions.assertTrue(tiles.get(i) instanceof Laboratory);
        }

        for(int i=16; i<18;i++){
            Assertions.assertTrue(tiles.get(i) instanceof Shelter);
        }

        for(int i=18; i<21;i++){
            Assertions.assertTrue(tiles.get(i) instanceof Storage);
        }

        verify(mapSpy).setNeighbors1();
        verify(mapSpy).setCollectables1();
    }

    @Test
    public void Test_SetNeighbors1(){
        map.createTiles1();

        List<Tile> tiles = map.getTiles();

        for(Tile item : tiles){
            Assertions.assertNotEquals(0, item.getAdjacentTiles().size());
        }
    }

    private void CheckStorageMaterials(String name, int id,  List<Tile> tiles, int expectedAmount){
        try {
            Field fMaterials = Storage.class.getDeclaredField("materials");

            fMaterials.setAccessible(true);

            ArrayList<Material> fMaterialsValue = (ArrayList<Material>) fMaterials.get(tiles.get(id));

            Assertions.assertEquals(expectedAmount,fMaterialsValue.size());

            for (Material item : fMaterialsValue) {
                Assertions.assertEquals(name,item.getName());
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void CheckLaboratoryMaterials(Agent agent, ArrayList<String> names){
        try {
            Field fMaterials = Agent.class.getDeclaredField("materials");

            fMaterials.setAccessible(true);

            ArrayList<Material> fMaterialsValue = (ArrayList<Material>) fMaterials.get(agent);

            for (Material item : fMaterialsValue) {
                Assertions.assertTrue(names.contains(item.getName()));
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_SetCollectables1(){
        map.createTiles1();

        List<Tile> tiles = map.getTiles();

        for(Tile tile : tiles){
            if(tile instanceof Storage) {
                if (tile.getId() == 18)
                    CheckStorageMaterials("TDP", 18, tiles,21);
                else if (tile.getId() == 19)
                    CheckStorageMaterials("Lizin", 19, tiles,21);
                else if (tile.getId() == 20)
                    CheckStorageMaterials("CDP", 20, tiles,21);
            }
            else if(tile instanceof Laboratory){
                ArrayList<String> names = new ArrayList<>();
                names.add("TDP");
                names.add("Lizin");
                names.add("CDP");
                Laboratory lab = (Laboratory) tile;
                CheckLaboratoryMaterials(lab.GetCollectable().getAgent(),names);
            }
            else if(tile instanceof Shelter){
                Shelter shelter = (Shelter) tile;
                Assertions.assertNotNull(shelter.GetCollectable());
            }
        }
    }

    @Test
    public void Test_CreateTiles2(){
        int type = 1;
        Map mapSpy = spy(map);

        mapSpy.createTiles2();

        List<Tile> tiles = mapSpy.getTiles();

        for(int i=0; i<27;i++){
            Assertions.assertFalse(tiles.get(i) instanceof Laboratory);
            Assertions.assertFalse(tiles.get(i) instanceof Shelter);
            Assertions.assertFalse(tiles.get(i) instanceof Storage);
        }

        for(int i=27; i<31;i++){
            Assertions.assertTrue(tiles.get(i) instanceof Laboratory);
        }

        for(int i=31; i<35;i++){
            Assertions.assertTrue(tiles.get(i) instanceof Shelter);
        }

        for(int i=35; i<41;i++){
            Assertions.assertTrue(tiles.get(i) instanceof Storage);
        }

        verify(mapSpy).setNeighbors2();
        verify(mapSpy).setCollectables2();
    }

    @Test
    public void Test_SetNeighbors2(){
        map.createTiles2();

        List<Tile> tiles = map.getTiles();

        for(Tile item : tiles){
            Assertions.assertNotEquals(0, item.getAdjacentTiles().size());
        }
    }

    @Test
    public void Test_SetCollectables2(){
        map.createTiles2();

        List<Tile> tiles = map.getTiles();

        for(Tile tile : tiles){
            if(tile instanceof Storage) {
                if (tile.getId() == 35)
                    CheckStorageMaterials("dUTP", 35, tiles,51);
                else if (tile.getId() == 36)
                    CheckStorageMaterials("CDP", 36, tiles,51);
                else if (tile.getId() == 37)
                    CheckStorageMaterials("TDP", 37, tiles,51);
                else if (tile.getId() == 38)
                    CheckStorageMaterials("Szerin", 38, tiles,51);
                else if (tile.getId() == 39)
                    CheckStorageMaterials("Lizin", 39, tiles,51);
                else if (tile.getId() == 40)
                    CheckStorageMaterials("Valin", 40, tiles,51);
            }
            else if(tile instanceof Laboratory){
                ArrayList<String> names = new ArrayList<>();
                names.add("TDP");
                names.add("Lizin");
                names.add("CDP");
                names.add("Valin");
                names.add("Szerin");
                names.add("dUTP");
                Laboratory lab = (Laboratory) tile;
                CheckLaboratoryMaterials(lab.GetCollectable().getAgent(),names);
            }
            else if(tile instanceof Shelter){
                Shelter shelter = (Shelter) tile;
                Assertions.assertNotNull(shelter.GetCollectable());
            }
        }
    }

    @Test
    public void Test_VirologistDie(){
        Virologist vMock = mock(Virologist.class);
        Game gMock = mock(Game.class);
        try {
            Field fVirologists = Map.class.getDeclaredField("virologists");
            Field fVirologistsCount = Map.class.getDeclaredField("virologistNumber");

            fVirologists.setAccessible(true);
            fVirologistsCount.setAccessible(true);

            ArrayList<Virologist> fVirologistsValue = (ArrayList<Virologist>) fVirologists.get(map);
            int fVirologistsCountValueBefore = (int) fVirologistsCount.get(map);

            fVirologistsValue.add(vMock);
            map.setGame(gMock);

            map.virologistDie(vMock);

            int fVirologistsCountValueAfter = (int) fVirologistsCount.get(map);
            Assertions.assertFalse(map.getVirologists().contains(vMock));
            Assertions.assertEquals(fVirologistsCountValueAfter,fVirologistsCountValueBefore-1);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
