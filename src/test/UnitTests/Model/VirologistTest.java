package UnitTests.Model;

import Control.*;
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
        bag.setSize(0);
        mainCharacter.setBag(bag);
        mainCharacter.setTile(shelter);
        mainCharacter.CollectProtectiveGear();
        assertTrue(mainCharacter.getBag().getUsedSize() == 0);
        bag.setSize(30);
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
        bag.setSize(0);
        mainCharacter.setBag(bag);
        mainCharacter.setTile(storage);
        mainCharacter.CollectMaterial();
        assertTrue(mainCharacter.getBag().getUsedSize() == 0);
        bag.setSize(30);
        mainCharacter.CollectMaterial();
        assertTrue(mainCharacter.getBag().getUsedSize() == 1);
    }

    @Test
    void addAgent() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        VitusDanceAgent agent = new VitusDanceAgent(materials,"vitusDance");
        Bag bag = new Bag();
        bag.setSize(0);
        mainCharacter.setBag(bag);
        mainCharacter.addAgent(agent);
        assertTrue(mainCharacter.getBag().getUsedSize() == 0);
        bag.setSize(30);
        mainCharacter.addAgent(agent);
        assertTrue(mainCharacter.getBag().getUsedSize() == 1);
    }

    @Test
    void unwear() {
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        Glove glove = new Glove("gloves");
        assertTrue(mainCharacter.getWear().isEmpty());
        mainCharacter.Wear(glove);
        assertTrue(!mainCharacter.getWear().isEmpty());
        mainCharacter.Unwear(glove);
        assertTrue(mainCharacter.getWear().isEmpty());

    }

    @Test
    void die() {
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Tile tile = new Tile(pointsX,pointsY,0);
        mainCharacter.setTile(tile);
        Game game = new Game();
        Map map = new Map();
        map.getVirologists().add(mainCharacter);
        map.setGame(game);
        mainCharacter.setMap(map);
        mainCharacter.Die();
        assertNull(mainCharacter.getTile());
        assertTrue(map.getVirologists().isEmpty());
    }

    @Test
    void useMaterials() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        boolean result = mainCharacter.useMaterials(materials);
        assertFalse(result);
        mainCharacter.getBag().getMaterials().addAll(materials);
        result = mainCharacter.useMaterials(materials);
        assertTrue(result);
    }

    @Test
    void addEffect() {
        VitusDance dance = new VitusDance();
        assertTrue(mainCharacter.getEffects().isEmpty());
        mainCharacter.addEffect(dance);
        assertFalse(mainCharacter.getEffects().isEmpty());
    }

    @Test
    void vitusDanceActionPerform() {
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Tile tile1 = new Tile(pointsX,pointsY,0);
        int targetID = 1;
        Tile tile2 = new Tile(pointsX,pointsY,targetID);
        tile2.setId(targetID);
        tile1.setNeighbor(tile2);
        mainCharacter.setTile(tile1);
        mainCharacter.VitusDanceActionPerform();
        assertTrue(mainCharacter.getTile().getN() == 0);
        VitusDance dance = new VitusDance();
        VirologistView view = new VirologistView();
        mainCharacter.setView(view);
        mainCharacter.addEffect(dance);
        mainCharacter.VitusDanceActionPerform();
        assertTrue(mainCharacter.getTile().getId() == 1);
    }

    @Test
    void bearDanceActionPerform() {
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        int[] pointsX = {0,0};
        int[] pointsY = {0,0};
        Tile tile1 = new Tile(pointsX,pointsY,0);
        Storage storage = new Storage(materials,pointsX,pointsY,1);
        tile1.setNeighbor(storage);
        mainCharacter.setTile(tile1);
        Virologist targetOnTile = new Virologist();
        targetOnTile.setTile(tile1);
        Virologist targetOnStorage = new Virologist();
        targetOnStorage.setTile(storage);
        Bag bag = new Bag();
        mainCharacter.setBag(bag);
        BearDanceAgent agent = new BearDanceAgent(materials,"beardance");
        mainCharacter.getBag().Add(agent);
        mainCharacter.getBag().Add(agent);
        mainCharacter.addEffect(new BearDance());

        VirologistView view = new VirologistView();
        mainCharacter.setView(view);

        assertTrue(targetOnTile.getEffects().isEmpty());
        assertTrue(targetOnStorage.getEffects().isEmpty());
        assertFalse(storage.getMaterials().isEmpty());

        mainCharacter.BearDanceActionPerform();
        assertFalse(targetOnTile.getEffects().isEmpty());
        assertFalse(targetOnStorage.getEffects().isEmpty());
        assertTrue(storage.getMaterials().isEmpty());

    }

    @Test
    void removeEffect() {
        assertTrue(mainCharacter.getEffects().isEmpty());
        VitusDance dance = new VitusDance();
        mainCharacter.addEffect(dance);
        assertFalse(mainCharacter.getEffects().isEmpty());
        mainCharacter.removeEffect(dance);
        assertTrue(mainCharacter.getEffects().isEmpty());
    }


    @Test
    void setCodeCount() {
        assertTrue(mainCharacter.getCodeCount() == 0);
        mainCharacter.setCodeCount(3);
        assertTrue(mainCharacter.getCodeCount() == 3);
    }

    @Test
    void setAgentResistance() {
        assertTrue(mainCharacter.getAgentResistance() == 0);
        mainCharacter.setAgentResistance(3.2);
        assertTrue(mainCharacter.getAgentResistance() == 3.2);
    }

    @Test
    void setThrowBackAvailable() {
        assertFalse(mainCharacter.isThrowBackAvailable());
        mainCharacter.setThrowBackAvailable(true);
        assertTrue(mainCharacter.isThrowBackAvailable());
    }

    @Test
    void setUntouchbale() {
        assertFalse(mainCharacter.getUntouchable());
        mainCharacter.setUntouchbale(true);
        assertTrue(mainCharacter.getUntouchable());
    }

    @Test
    void setId() {
        assertTrue(mainCharacter.getId() == 0);
        mainCharacter.setId(2);
        assertTrue(mainCharacter.getId() == 2);
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

}