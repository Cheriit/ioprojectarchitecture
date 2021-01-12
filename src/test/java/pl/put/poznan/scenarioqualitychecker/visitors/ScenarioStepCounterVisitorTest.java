package pl.put.poznan.scenarioqualitychecker.visitors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScenarioStepCounterVisitorTest {
    public ScenarioStepCounterVisitor visitor;

    @BeforeEach
    private void setUp()
    {
        visitor = new ScenarioStepCounterVisitor();
    }

    @Test
    void propagateVisitor_step()
    {
        Step scenario = new Step("Content 1");

        scenario.accept(visitor);

        assertEquals(visitor.getCounter(), 1);
    }

    @Test
    void propagateVisitor_scenario_content()
    {
        Scenario scenario = new Scenario("Content 1");

        scenario.addStep(new Step("Step 1"));
        scenario.addStep(new Step("Step 2"));

        scenario.accept(visitor);

        assertEquals(visitor.getCounter(), 3);
    }

    @Test
    void propagateVisitor_scenario_empty()
    {
        Scenario scenario = new Scenario("Content 1");
        scenario.accept(visitor);

        assertEquals(visitor.getCounter(), 1);
    }

    @Test
    void propagateVisitor_empty()
    {
        MainScenario scenario = new MainScenario("Scenariusz testowy pusty");

        scenario.accept(visitor);

        assertEquals(visitor.getCounter(), 1);
    }


    @Test
    void propagateVisitor_flat()
    {
        MainScenario scenario = new MainScenario("Scenariusz testowy pusty");
        scenario.addStep(new Step("Content 1"));
        scenario.addStep(new Step("Content 2"));
        scenario.addStep(new Step("Content 3"));

        scenario.accept(visitor);

        assertEquals(visitor.getCounter(), 4);
    }

    @Test
    void propagateVisitor_multiLever()
    {
        MainScenario scenario = new MainScenario("Scenariusz testowy pusty");
        Scenario subScenario = new Scenario("Subscenario 1");
        scenario.addStep(new Step("Content 1"));
        scenario.addStep(new Step("Content 2"));
        scenario.addStep(new Step("Content 3"));
        subScenario.addStep(new Step("Content 4"));
        subScenario.addStep(new Step("Content 5"));
        scenario.addStep(subScenario);
        scenario.accept(visitor);

        assertEquals(visitor.getCounter(), 7);
    }
}