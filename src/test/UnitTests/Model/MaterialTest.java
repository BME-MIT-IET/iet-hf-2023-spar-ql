package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaterialTest {
    Material material;

    @BeforeEach
    void setup(){
        material = new Material("material");
        Virologist virologist = new Virologist();
        Bag bag = new Bag();
        ArrayList<Material> materials = new ArrayList<Material>(
                Arrays.asList(
                        new Material("material"),
                        new Material("Mezoplaticin"))
        );
        bag.getMaterials().addAll(materials);
        virologist.setBag(bag);
        material.setVirologist(virologist);
    }

    @Test
    void lessMaterial(){
        assertTrue(material.getVirologist().getBag().getUsedSize() == 2);
        material.lessMaterial(2);
        long result = material.getVirologist().getBag().getUsedSize();
        assertTrue(result == 1);
    }

    // throws error because Material does not implement the Cloneable interface
    @Test
    void addMaterial(){
        assertTrue(material.getVirologist().getBag().getUsedSize() == 2);
        try {
            material.addMaterial(3);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertTrue(material.getVirologist().getBag().getUsedSize() == 5);

    }
}
