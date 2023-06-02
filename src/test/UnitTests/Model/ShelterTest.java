package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ShelterTest {

    private int[] pointsX = {0,0};
    private int[] pointsY = {0,0};

    private Shelter shelter;

    private ProtectiveGear pgMock;

    @BeforeEach
    public void setup(){
        pgMock=mock(ProtectiveGear.class);
        shelter = new Shelter(pgMock,pointsX,pointsY ,1);
    }

    @Test
    public void Test_GetCollectable(){
        Shelter spyShelter = spy(shelter);

        ProtectiveGear gc = spyShelter.GetCollectable();

        Assertions.assertEquals(pgMock,gc);
        verify(spyShelter).Collect();
    }
}
