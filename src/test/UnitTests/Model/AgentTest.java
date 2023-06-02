package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgentTest  {



    Virologist dummyVir = new Virologist() {
        Effects dummyEffect;

        @Override
        public void addEffect(Effects e) {
            dummyEffect = e;
        }

        @Override
        public List<Effects> getEffects() {
            return Arrays.asList(dummyEffect);
        }
    };
    ArrayList<Material> input = new ArrayList<Material>(
            Arrays.asList(
                    new Material(" Ribolutin"),
                    new Material(" Mezoplaticin"))
    );
    private class StubAgent extends Agent  {
          public StubAgent(ArrayList<Material> m, String name) {
            super(m, name);
        }

        @Override
        public void setStatus(long n, Virologist v) {

        }

        public Bag getBagPublic(){
            return this.bag;
        }
    };

    StubAgent stubTestAgent = new StubAgent(input, "Lum-Fao Flue");
    @Test
    void setBag() {
        Bag bag = new Bag();
        stubTestAgent.setBag(bag);
        assertNotNull( stubTestAgent.getBagPublic());
        assertEquals(bag, stubTestAgent.getBagPublic());
    }

    @Test
    void stepTest() {
        Bag dummyBag = new Bag() {
            ArrayList<Agent> dummyAgentList = new ArrayList<>(
                    Arrays.asList(
                            stubTestAgent
                    )
            );

            @Override
            public ArrayList<Agent> getAgents() {
                return dummyAgentList;
            }
        };

        stubTestAgent.setBag(dummyBag);
        for (int i = 0; i < 4; i++) {
            stubTestAgent.Step();
        }
        assertNotNull(stubTestAgent);
        assertNotNull(stubTestAgent.getBagPublic().getAgents().get(0));
        assertEquals(stubTestAgent, stubTestAgent.getBagPublic().getAgents().get(0));
        stubTestAgent.Step();
        assertNotNull(stubTestAgent);
        assertTrue( stubTestAgent.getBagPublic().getAgents().isEmpty());
    }
    @Test
    void getNeededMaterials() {
        assertEquals(input, stubTestAgent.getNeededMaterials());
        assertEquals(input.get(0), stubTestAgent.getNeededMaterials().get(0));
        assertEquals(input.get(1), stubTestAgent.getNeededMaterials().get(1));
    }

    @Test
    void setMaterial() {
        Material dummyMaterial = new Material("Vorulium") {
            public String getName() {
                return this.name;
            }
        };
        stubTestAgent.setMaterial(dummyMaterial);
        assertEquals(dummyMaterial.getName(), stubTestAgent.getNeededMaterials().get(2).getName());
    }
}