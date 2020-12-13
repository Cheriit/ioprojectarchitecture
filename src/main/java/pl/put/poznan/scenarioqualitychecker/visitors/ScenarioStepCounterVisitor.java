package pl.put.poznan.scenarioqualitychecker.visitors;

import org.slf4j.LoggerFactory;

import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

/**
 * ScenarioStepCounterVisitor.java
 *
 * Class implementing Visitor interface.
 * Class counting visited steps in a given scenario.
 */
public class ScenarioStepCounterVisitor implements Visitor {

    private Integer step_counter = 0;
    public ScenarioStepCounterVisitor() {}

    /**
     * Visit action on a specific step, counting performed steps.
     * @param step Currently visiting step
     */
    @Override
    public void visit(Step step) {
        if (step != null) {
            step_counter++;
            LoggerFactory.getLogger(ScenarioStepCounterVisitor.class).debug(String.format("Found new step no. %d: %s.", step_counter, step.getText()));
        } else {
            LoggerFactory.getLogger(ScenarioStepCounterVisitor.class).debug("Visited null value.");
        }
    }

    public Integer getCounter() { return step_counter; }

    /**
     * Method resetting counter.
     */
    public void resetCounter() { step_counter = 0; }
}
