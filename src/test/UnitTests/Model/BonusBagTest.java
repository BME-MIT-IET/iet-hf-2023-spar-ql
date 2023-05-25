package UnitTests.Model;

import Model.Bag;
import Model.BonusBag;
import Model.Virologist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class BonusBagTest {

    private BonusBag bonusBag;

    @BeforeEach
    public void setup(){
        bonusBag = new BonusBag("Test BonusBag");
    }

    @Test
    public void Test_SetAttribute(){
        Virologist spyVirologist = spy(Virologist.class);
        Bag spyBag = spy(Bag.class);
        spyVirologist.setBag(spyBag);
        bonusBag.setVirologist(spyVirologist);

        bonusBag.setAttribute(spyVirologist);

        verify(spyVirologist).getBag();
        verify(spyBag).setBonusSize(bonusBag.getBonus());
        Assertions.assertEquals(50,spyBag.getSize());
    }

    @Test
    public void Test_TakeAway(){
        Virologist spyVirologist = spy(new Virologist());
        Bag spyBag = spy(new Bag());
        spyVirologist.setBag(spyBag);
        bonusBag.setVirologist(spyVirologist);

        bonusBag.takeAway(spyVirologist);

        verify(spyVirologist).getBag();
        verify(spyVirologist).Unwear(bonusBag);
        verify(spyBag).setSize(-bonusBag.getBonus());
    }

    @Test
    public void Test_Wear(){
        Virologist spyVirologist = spy(new Virologist());
        Bag spyBag = spy(new Bag());
        spyVirologist.setBag(spyBag);
        BonusBag spyBonusBag = spy(bonusBag);
        spyBonusBag.setVirologist(spyVirologist);

        spyBonusBag.Wear();

        verify(spyBonusBag).setAttribute(spyVirologist);
    }

    @Test
    public void Test_Destroy(){
        Virologist vMock = mock(Virologist.class);
        BonusBag bagSpy = spy(bonusBag);
        bagSpy.setVirologist(vMock);
        doNothing().when(bagSpy).takeAway(any(Virologist.class));

        bagSpy.Destroy();

        verify(bagSpy).takeAway(any(Virologist.class));
    }
}
