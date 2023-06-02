package UnitTests.Model;

import Model.Effects;
import Model.Paralyzed;
import Model.VitusDance;
import Model.VitusDanceAgent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VitusDanceAgentTest extends AgentTest {

    VitusDanceAgent vitusDanceTestAgent;
    @BeforeEach
    void setUp() {
        vitusDanceTestAgent = new VitusDanceAgent(input,"Imbaditis Jacksimus");
    }

    @Test
    void setStatus() {
        vitusDanceTestAgent.setStatus(5, dummyVir);
        Effects getEffect = dummyVir.getEffects().get(0);
        Assertions.assertEquals(new VitusDance( ).getId(),getEffect.getId() );
        assertEquals(5,dummyVir.getEffects().get(0).getTime());
    }

    @Test
    void getDuration() {
        assertEquals(1, vitusDanceTestAgent.getDuration());
    }

    @Test
    void setDuration() {
        assertEquals(1, vitusDanceTestAgent.getDuration());
        vitusDanceTestAgent.setDuration(vitusDanceTestAgent.getDuration()+4);
        assertEquals(5, vitusDanceTestAgent.getDuration());
    }
}