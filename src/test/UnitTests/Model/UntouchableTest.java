package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UntouchableTest extends EffectsTest {

    Untouchable testAgent;

    @BeforeEach
    void setUp() {
        testAgent = new Untouchable() ;
    }



    @Test
    void testGetSetTime() {
        Virologist vMock  =    mock(Virologist.class);
        testAgent.setVirologist(vMock);
        assertEquals(0, testAgent.getTime());
        testAgent.setTimeEffected(1);
        testAgent.setVirologist(vMock);
        assertEquals(1,testAgent.getTime());
    }

    @Test
    void stepNormal() {

        assertEquals(0, testAgent.getTime());
        testAgent.setVirologist(dummyVir);
        testAgent.setTimeEffected(8);
        testAgent.Step();
        assertEquals(7, testAgent.getTime());
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
    @Test
    void stepWithoutVirologistAssigned() {

        assertEquals(0, testAgent.getTime());
        testAgent.setTimeEffected(8);
        testAgent.Step();
        assertEquals(7, testAgent.getTime());
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