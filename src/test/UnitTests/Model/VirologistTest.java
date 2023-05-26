package UnitTests.Model;

import Model.*;
import View.VirologistView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VirologistTest {
    Virologist mainCharacter;
    @BeforeEach
    void setUp() {
        mainCharacter = new Virologist();
    }

    @Test
    void setBag() {
        assertNull(mainCharacter.getBag());
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        assertNotNull(mainCharacter.getBag());
    }

    @Test
    void setTile() {
        assertNull(mainCharacter.getTile());
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Tile tile = new Tile(pointsX,pointsY,0);
        mainCharacter.setTile(tile);
        assertNotNull(tile);
    }

    @Test
    void setMap() {
        assertNull(mainCharacter.getMap());
        Map map = new Map();
        mainCharacter.setMap(map);
        assertNotNull(mainCharacter.getMap());
    }

    @Test
    void setView() {
        assertNull(mainCharacter.getView());
        VirologistView view = new VirologistView();
        mainCharacter.setView(view);
        assertNotNull(mainCharacter.getView());
    }

    @Test
    void move() {
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Tile tile1 = new Tile(pointsX,pointsY,0);
        int targetID = 1;
        Tile tile2 = new Tile(pointsX,pointsY,targetID);
        tile2.setId(targetID);
        tile1.setNeighbor(tile2);
        mainCharacter.setTile(tile1);
        assertTrue(mainCharacter.Move(targetID));
        assertTrue(targetID == mainCharacter.getTile().getN());
    }

    @Test
    void useAgent() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        VitusDanceAgent agent = new VitusDanceAgent(materials,"vitusDance");
        mainCharacter.addAgent(agent);
        Virologist target = new Virologist();
        mainCharacter.UseAgent(target,agent);
        assertFalse(target.getEffects().isEmpty());
        assertTrue(mainCharacter.getBag().getUsedSize() == 0);
    }

    @Test
    void takeGear() {
        Virologist target = new Virologist();
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();
        Glove glove = new Glove("gloves");
        bag2.Add(glove);
        mainCharacter.setBag(bag1);
        target.setBag(bag2);
        target.Wear(glove);
        bag2.setVirologist(target);
        mainCharacter.TakeGear(target,glove);
        assertTrue(mainCharacter.getBag().getUsedSize() == 1);
        assertTrue(target.getBag().getUsedSize() == 0);
    }

    @Test
    void learnCode() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        VitusDanceAgent agent = new VitusDanceAgent(materials,"vitusDance");
        GeneticCode code = new GeneticCode(agent);
        mainCharacter.LearnCode(code);
        assertTrue(mainCharacter.getGeneticCodes().size() == 1);
        assertTrue(mainCharacter.getCodeCount() == 1);
    }

    @Test
    void collectProtectiveGear() {
        Glove glove = new Glove("gloves");
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Shelter shelter = new Shelter(glove,pointsX,pointsY,0);
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        mainCharacter.setTile(shelter);
        mainCharacter.CollectProtectiveGear();
        assertTrue(mainCharacter.getBag().getUsedSize() == 1);
    }

    @Test
    void palpateWall() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        VitusDanceAgent agent = new VitusDanceAgent(materials,"vitusDance");
        GeneticCode code = new GeneticCode(agent);
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Laboratory lab = new Laboratory(code,pointsX,pointsY,0);
        mainCharacter.setTile(lab);
        mainCharacter.PalpateWall();
        assertTrue(mainCharacter.getGeneticCodes().size() == 1);
    }

    @Test
    void wear() {
        Glove glove = new Glove("gloves");
        Bag bag = new Bag();
        bag.Add(glove);
        mainCharacter.setBag(bag);
        mainCharacter.Wear(glove);
        assertTrue(mainCharacter.getWear().size() == 1);
    }

    @Test
    void hitByAgent() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        VitusDanceAgent agent = new VitusDanceAgent(materials,"vitusDance");
        mainCharacter.HitByAgent(agent);
        assertTrue(mainCharacter.getEffects().size() == 1);
    }

    @Test
    void giveGear() {
        Glove glove = new Glove("gloves");
        Bag bag = new Bag();
        bag.Add(glove);
        bag.setVirologist(mainCharacter);
        mainCharacter.setBag(bag);
        mainCharacter.giveGear(glove);
        assertTrue(mainCharacter.getBag().getUsedSize() == 0);
    }

    @Test
    void collectMaterial() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Storage storage = new Storage(materials,pointsX,pointsY,0);
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        mainCharacter.setTile(storage);
        mainCharacter.CollectMaterial();
        assertTrue(mainCharacter.getBag().getUsedSize() == 1);
    }

    @Test
    void addAgent() {
    }

    @Test
    void unwear() {
    }

    @Test
    void die() {
    }

    @Test
    void useMaterials() {
    }

    @Test
    void addEffect() {
    }

    @Test
    void vitusDanceActionPerform() {
    }

    @Test
    void bearDanceActionPerform() {
    }

    @Test
    void getGeneticCodes() {
    }

    @Test
    void setCodeCount() {
    }

    @Test
    void setAgentResistance() {
    }

    @Test
    void setThrowBackAvailable() {
    }

    @Test
    void getAgentResistance() {
    }

    @Test
    void isThrowBackAvailable() {
    }

    @Test
    void setUntouchbale() {
    }


    @Test
    void getCodeCount() {
    }

    @Test
    void setId() {
    }

    @Test
    void print() {
    }

    @Test
    void removeEffect() {
    }

    @Test
    void getBearDance() {
    }

    @Test
    void getVitusDance() {
    }
}