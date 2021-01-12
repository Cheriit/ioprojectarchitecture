package pl.put.poznan.scenarioqualitychecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepthLimitedTest {
    MainScenario m;
    @BeforeEach
    void setUp() {
        m = new MainScenario("");
    }
    @Test
    void depth0OneLevelTest() {
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(0);
        assertEquals(0,m2.getSteps().size());
    }
    @Test
    void depth0TreeLinearTest() {
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(0);
        assertEquals(0,m2.getSteps().size());
    }

    @Test
    void depth0TreeBranchedTest() {
        m.addStep(new Step());
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(0);
        assertEquals(0,m2.getSteps().size());
    }

    @Test
    void depth0TreeMultilevelTest() {
        m.addStep(new Step());
        m.addStep(new Step());
        Scenario s = new Scenario("");
        s.addStep(new Step());
        m.addStep(s);
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(0);
        assertEquals(0,m2.getSteps().size());
    }

    @Test
    void depth1TreeOneLevel() {
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(1);
        assertTrue(m2.getSteps().isEmpty());
    }

    @Test
    void depth1TreeLinearTest() {
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(1);
        assertEquals(1,m2.getSteps().size());
    }

    @Test
    void depth1TreeBranchedTest() {
        m.addStep(new Step());
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(1);
        assertEquals(2,m2.getSteps().size());
    }

    @Test
    void depth1TreeMultilevelTest() {
        m.addStep(new Step());
        m.addStep(new Step());
        Scenario s = new Scenario("");
        s.addStep(new Step());
        m.addStep(s);
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(1);
        assertEquals(3,m2.getSteps().size());
        assertEquals(0,((Scenario)m2.getSteps().get(2)).getSteps().size());
    }

    @Test
    void depth2TreeOneLevel() {
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(2);
        assertTrue(m2.getSteps().isEmpty());
    }

    @Test
    void depth2TreeLinearTest() {
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(2);
        assertEquals(1,m2.getSteps().size());
    }

    @Test
    void depth2TreeBranchedTest() {
        m.addStep(new Step());
        m.addStep(new Step());
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(2);
        assertEquals(2,m2.getSteps().size());
    }

    @Test
    void depth2TreeMultilevelTest() {
        m.addStep(new Step());
        m.addStep(new Step());
        Scenario s = new Scenario("");
        s.addStep(new Step());
        m.addStep(s);
        MainScenario m2 = (MainScenario)m.getLimitedDepthCopy(2);
        assertEquals(3,m2.getSteps().size());
        assertEquals(1,((Scenario)m2.getSteps().get(2)).getSteps().size());
    }
}
