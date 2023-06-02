package UnitTests.Model;

import Model.Bag;
import Model.Cape;
import Model.Virologist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CapeTest {

    private Cape cape;

    @BeforeEach
    public void setup(){
        cape = new Cape("Test Cape");
    }

    @Test
    public void Test_SetAttribute(){
        Virologist spyVirologist = spy(Virologist.class);
        cape.setVirologist(spyVirologist);

        cape.setAttribute(spyVirologist);

        verify(spyVirologist).setAgentResistance(cape.getBonus());
    }

    @Test
    public void Test_TakeAway(){
        Virologist spyVirologist = spy(new Virologist());
        cape.setVirologist(spyVirologist);

        cape.takeAway(spyVirologist);

        verify(spyVirologist).Unwear(cape);
        verify(spyVirologist).setAgentResistance(-cape.getBonus());
    }

    @Test
    public void Test_Wear(){
        Virologist spyVirologist = spy(new Virologist());
        Bag spyBag = spy(new Bag());
        spyVirologist.setBag(spyBag);
        Cape spyCape = spy(cape);
        spyCape.setVirologist(spyVirologist);

        spyCape.Wear();

        verify(spyCape).setAttribute(spyVirologist);
    }

    @Test
    public void Test_Destroy(){
        Virologist vMock = mock(Virologist.class);
        Cape capeSpy = spy(cape);
        capeSpy.setVirologist(vMock);
        doNothing().when(capeSpy).takeAway(any(Virologist.class));

        capeSpy.Destroy();

        verify(capeSpy).takeAway(any(Virologist.class));
    }
}
