package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ForgetAgentTest extends AgentTest {
    Agent dummyAgent = new Agent(new ArrayList<Material>(
            Arrays.asList(
                    new Material(" Ribolutin"),
                    new Material(" Mezoplaticin"))
    ),
           "Monosedative Stress syndrome" ) {


        @Override
        public void setStatus(long n, Virologist v) {

        }
    };

    Virologist dummyVir = new Virologist() {
        ArrayList<GeneticCode> dummyGeneticCodes = new ArrayList<> (
                Arrays.asList(
                        new GeneticCode( dummyAgent),
                        new GeneticCode( dummyAgent)
                )
        );
        int dummyCodeCount = 2;

        @Override
        public ArrayList<GeneticCode> getGeneticCodes() {
            return dummyGeneticCodes;
        }

        @Override
        public void setCodeCount(int n) {
            dummyCodeCount = n;
        }

        @Override
        public long getCodeCount() {
            return dummyCodeCount;
        }
    };

    ForgetAgent testForgetAgent;

    @BeforeEach
    void setUp() {
        testForgetAgent = new ForgetAgent(input, "Mediative Derangament Syndrome" );
    }

    @Test
    void setStatus() {
        assertFalse(dummyVir.getGeneticCodes().isEmpty());
        assertEquals(2, dummyVir.getGeneticCodes().size() );
        testForgetAgent.setStatus(3, dummyVir);
        assertTrue(dummyVir.getGeneticCodes().isEmpty());
        assertEquals(0, dummyVir.getGeneticCodes().size() );
        assertEquals(dummyVir.getCodeCount(), dummyVir.getGeneticCodes().size() );
    }
}