package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParalyzeAgentTest {

    Virologist dummyVir = new Virologist() {
        Effects dummyEffect;

        @Override
        public void addEffect(Effects e) {
            super.addEffect(e);
        }

        @Override
        public List<Effects> getEffects() {
            return Arrays.asList(dummyEffect);
        }
    };


    ParalyzeAgent testAgent;



    @BeforeEach
    void setUp() {
        ArrayList<Material> input = new ArrayList<Material>(
                Arrays.asList(
                        new Material(" Ribolutin"),
                        new Material(" Mezoplaticin"))
        );
        dummyVir = new Virologist();
        testAgent = new ParalyzeAgent(input, "Sobavirus");
    }


    @Test
    void setStatus() {
        testAgent.setStatus(5, dummyVir);
        assertEquals(testAgent,dummyVir.getEffects().get(0));
        assertEquals(5,dummyVir.getEffects().get(0).getTime());
    }

    @Test
    void getDuration() {
        assertEquals(1,testAgent.getDuration());
    }

    @Test
    void setDuration() {
        assertEquals(1,testAgent.getDuration());
        testAgent.setDuration(testAgent.getDuration()+4);
        assertEquals(4,testAgent.getDuration());
    }
}