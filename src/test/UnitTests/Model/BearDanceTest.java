package UnitTests.Model;

import Model.BearDance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BearDanceTest extends EffectsTest {

    BearDance bearDanceAgentTets;

    @BeforeEach
    void setUp (){
        bearDanceAgentTets = new BearDance();
    }

    @Test
    void getSetTimeTest() {
        assertEquals(0, bearDanceAgentTets.getTime());
        bearDanceAgentTets.setTimeEffected(1);
        assertEquals(1,bearDanceAgentTets.getTime());
    }
    @Test
    void step() {
        assertEquals(0, bearDanceAgentTets.getTime());
        bearDanceAgentTets.setTimeEffected(8);
        bearDanceAgentTets.Step();
        assertEquals(7, bearDanceAgentTets.getTime());
        bearDanceAgentTets.setVirologist(dummyVir);
        dummyVir.addEffect(bearDanceAgentTets);
        for(int i = 0; i < 6; i++){
            bearDanceAgentTets.Step();
            assertEquals(bearDanceAgentTets, dummyVir.getEffects().get(0));
        }
        bearDanceAgentTets.Step();
        assertNull(dummyVir.getEffects().get(0));
    }
}