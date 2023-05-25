package UnitTests.Model;

import Model.VitusDance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VitusDanceTest extends EffectsTest {

    VitusDance vitusDanceTestAgent;

    @BeforeEach
    void setUp() {
        vitusDanceTestAgent = new VitusDance();
    }

    @Test
    void step() {
        assertEquals(0, vitusDanceTestAgent.getTime());
        vitusDanceTestAgent.setTimeEffected(8);
        vitusDanceTestAgent.Step();
        assertEquals(7, vitusDanceTestAgent.getTime());
        vitusDanceTestAgent.setVirologist(dummyVir);
        dummyVir.addEffect(vitusDanceTestAgent);
        for(int i = 0; i < 6; i++){
            vitusDanceTestAgent.Step();
            assertEquals(vitusDanceTestAgent, dummyVir.getEffects().get(0));
        }
        vitusDanceTestAgent.Step();
        assertNull(dummyVir.getEffects().get(0));
    }

    @Test
    void getSetTimeTest() {
        assertEquals(0, vitusDanceTestAgent.getTime());
        vitusDanceTestAgent.setTimeEffected(1);
        assertEquals(1,vitusDanceTestAgent.getTime());
    }
}