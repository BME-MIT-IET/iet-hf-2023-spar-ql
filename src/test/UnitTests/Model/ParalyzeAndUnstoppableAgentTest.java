package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParalyzeAndUnstoppableAgentTest extends AgentTest{


    ParalyzeAgent testParalyzeAgent;
    UntouchableAgent testUntouchableAgent;
    @BeforeEach
    void SetUp() {
        dummyVir = new Virologist();
        testParalyzeAgent = new ParalyzeAgent(input, "Sobavirus");
        testUntouchableAgent = new UntouchableAgent(input, "Acute Memetic Overstimulation");
    }


    @Test
    void setParalyzeStatus() {
        testParalyzeAgent.setStatus(5, dummyVir);
        Effects getEffect = dummyVir.getEffects().get(0);
        Assertions.assertEquals(new Paralyzed().getId(),getEffect.getId() );
        assertEquals(5,dummyVir.getEffects().get(0).getTime());
    }

    @Test
    void getParalyzeDuration() {
        assertEquals(1, testParalyzeAgent.getDuration());
    }

    @Test
    void setParalyzeDuration() {
        assertEquals(1, testParalyzeAgent.getDuration());
        testParalyzeAgent.setDuration(testParalyzeAgent.getDuration()+4);
        assertEquals(5, testParalyzeAgent.getDuration());
    }

    @Test
    void setUntouchableStatus() {
        testUntouchableAgent.setStatus(5, dummyVir);
        Effects getEffect = dummyVir.getEffects().get(0);
        Assertions.assertEquals(new Untouchable().getId(),getEffect.getId() );
        assertEquals(5,dummyVir.getEffects().get(0).getTime());
    }

    @Test
    void setUntouchableDuration() {
        assertEquals(1, testUntouchableAgent.getDuration());
        testUntouchableAgent.setDuration(testUntouchableAgent.getDuration()+4);
        assertEquals(5, testUntouchableAgent.getDuration());
    }

    @Test
    void getUntouchableDuration() {
        assertEquals(1, testUntouchableAgent.getDuration());
    }
}