package UnitTests.Model;

import Model.BearDance;
import Model.BearDanceAgent;
import Model.Effects;
import Model.Paralyzed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BearDanceAgentTest extends AgentTest {
    BearDanceAgent bearDanceTestAgent;

    @BeforeEach
    void setUp() {
        bearDanceTestAgent = new BearDanceAgent(input, "Medvefication");
    }

    @Test
    void setStatus() {
        bearDanceTestAgent.setStatus(5, dummyVir);
        Effects getEffect = dummyVir.getEffects().get(0);
        Assertions.assertEquals(new BearDance().getId(), getEffect.getId() );
        assertEquals(5,dummyVir.getEffects().get(0).getTime());
    }

    @Test
    void getDuration() {
        assertEquals(Long.MAX_VALUE, bearDanceTestAgent.getDuration());
    }

    @Test
    void setDuration() {
        assertEquals(Long.MAX_VALUE, bearDanceTestAgent.getDuration());
        bearDanceTestAgent.setDuration(4);
        assertNotEquals(Long.MAX_VALUE, bearDanceTestAgent.getDuration());
        assertEquals(4, bearDanceTestAgent.getDuration());
    }
}