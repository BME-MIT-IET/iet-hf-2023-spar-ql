package UnitTests.Model;

import Model.Effects;
import Model.Paralyzed;
import Model.Virologist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParalyzedTest extends EffectsTest {

    Paralyzed paralyzedTestAgent;

    @BeforeEach
    void setUp() {
        paralyzedTestAgent = new Paralyzed() ;
    }
    @Test
    void SetTimeGetTime() {
        assertEquals(0, paralyzedTestAgent.getTime());
        paralyzedTestAgent.setTimeEffected(1);
        assertEquals(1,paralyzedTestAgent.getTime());
    }

    @Test
    void step() {
        assertEquals(0, paralyzedTestAgent.getTime());
        paralyzedTestAgent.setTimeEffected(8);
        paralyzedTestAgent.Step();
        assertEquals(7, paralyzedTestAgent.getTime());
        paralyzedTestAgent.setVirologist(dummyVir);
        dummyVir.addEffect(paralyzedTestAgent);
        for(int i = 0; i < 6; i++){
            paralyzedTestAgent.Step();
            assertEquals(paralyzedTestAgent, dummyVir.getEffects().get(0));
        }
        paralyzedTestAgent.Step();
        assertNull(dummyVir.getEffects().get(0));
    }
}