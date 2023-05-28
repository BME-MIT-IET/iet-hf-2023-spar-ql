package UnitTests.Model;

import Model.Untouchable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UntouchableTest extends EffectsTest {

    Untouchable testAgent;

    @BeforeEach
    void setUp() {
        testAgent = new Untouchable() ;
    }



    @Test
    void testGetSetTime() {
        assertEquals(0, testAgent.getTime());
        testAgent.setVirologist(dummyVir);

        testAgent.setTimeEffected(1);
        assertEquals(1,testAgent.getTime());
    }

    @Test
    void step() {
        assertEquals(0, testAgent.getTime());
        testAgent.setVirologist(dummyVir);

        testAgent.setTimeEffected(8);
        testAgent.Step();
        assertEquals(7, testAgent.getTime());
        assertTrue(dummyVir.getUntouchable());
        dummyVir.addEffect(testAgent);
        for(int i = 0; i < 6; i++){
            testAgent.Step();
            assertEquals(testAgent, dummyVir.getEffects().get(0));
            assertTrue(dummyVir.getUntouchable());
        }
        testAgent.Step();
        assertNull(dummyVir.getEffects().get(0));
        assertFalse(dummyVir.getUntouchable());
    }
}