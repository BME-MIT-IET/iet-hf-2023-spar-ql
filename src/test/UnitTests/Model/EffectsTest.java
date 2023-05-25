package UnitTests.Model;

import Model.Effects;
import Model.Virologist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EffectsTest {

    Virologist dummyVir = new Virologist() {

        Effects dummyEffect = null;
        @Override
        public void addEffect(Effects e) {
            dummyEffect = e ;
        }

        @Override
        public void removeEffect(Effects effect) {
            dummyEffect = null;
        }

        @Override
        public List<Effects> getEffects() {
            return Arrays.asList(dummyEffect);
        }
    };

    Effects stubEffect = new Effects() {
        @Override
        public void Step() {

        }
        @Override
        public void setTimeEffected(long n) {

        }
        @Override
        public long getTime() {
            return 0;
        }

    };

    @BeforeEach
    void setUp() {
    }

    @Test
    void setIdAndGetId() {
        assertNotNull(stubEffect.getId());
        int currId = stubEffect.getId() ;
        assertEquals(currId, stubEffect.getId());
        stubEffect.setId(800);
        assertEquals(800, stubEffect.getId());
    }
}