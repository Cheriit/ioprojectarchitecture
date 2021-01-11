package pl.put.poznan.scenarioqualitychecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

import static org.mockito.Mockito.*;

public class AcceptorTest {
    private Visitor mockedVisitor;

    @BeforeEach
    void setUp()
    {
        mockedVisitor = mock(Visitor.class);
    }

    @Test
    void propagateVisitor_step()
    {
        Step scenario = new Step("Content 1");

        scenario.accept(mockedVisitor);

        verify(mockedVisitor, times(1)).visit(any());
    }

    @Test
    void propagateVisitor_scenario_content()
    {
        Scenario scenario = new Scenario("Content 1");
        Step mockedStep_1 = mock(Step.class);
        Step mockedStep_2 = mock(Step.class);

        scenario.addStep(mockedStep_1);
        scenario.addStep(mockedStep_2);

        scenario.accept(mockedVisitor);

        verify(mockedVisitor, times(1)).visit(any());
        verify(mockedStep_1, times(1)).accept(any());
        verify(mockedStep_2, times(1)).accept(any());
    }

    @Test
    void propagateVisitor_scenario_empty()
    {
        Scenario scenario = new Scenario("Content 1");
        scenario.accept(mockedVisitor);

        verify(mockedVisitor, times(1)).visit(any());
    }

    @Test
    void propagateVisitor_empty()
    {
        MainScenario scenario = new MainScenario("Scenariusz testowy pusty");

        scenario.accept(mockedVisitor);

        verify(mockedVisitor, times(1)).visit(any());
    }


    @Test
    void propagateVisitor_flat()
    {
        MainScenario scenario = new MainScenario("Scenariusz testowy pusty");
        scenario.addStep(new Step("Content 1"));
        scenario.addStep(new Step("Content 2"));
        scenario.addStep(new Step("Content 3"));

        scenario.accept(mockedVisitor);

        verify(mockedVisitor, times(4)).visit(any());
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
        scenario.accept(mockedVisitor);

        verify(mockedVisitor, times(7)).visit(any());
    }

}
