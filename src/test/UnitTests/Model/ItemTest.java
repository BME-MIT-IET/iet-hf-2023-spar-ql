package UnitTests.Model;

import Model.Glove;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {

    private Glove glove;

    @BeforeEach
    public void setup(){
        glove = new Glove("Test Glove");

    }

    @Test
    public void Test_ItemEqual_WhenItemsAreDifferent(){
        Glove diffGlove = new Glove("Different Glove");

        boolean value = glove.ItemEqual(diffGlove);

        Assertions.assertFalse(value);
    }

    @Test
    public void Test_ItemEqual_WhenItemsAreSame(){
        Glove sameGlove = new Glove("Test Glove");

        boolean value = glove.ItemEqual(sameGlove);

        Assertions.assertTrue(value);
    }
}
