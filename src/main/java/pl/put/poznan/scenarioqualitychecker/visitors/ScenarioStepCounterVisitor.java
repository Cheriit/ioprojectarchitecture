package pl.put.poznan.scenarioqualitychecker.visitors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

/**
 * ScenarioStepCounterVisitor.java
 *
 * Class implementing Visitor interface.
 * Class counting visited steps in a given scenario.
 */
public class ScenarioStepCounterVisitor implements Visitor {

    private Integer stepCounter = 0;
    Logger logger = LoggerFactory.getLogger(ScenarioStepCounterVisitor.class);
    
    public ScenarioStepCounterVisitor() {}

    /**
     * Visit action on a specific step, counting performed steps.
     * @param step Currently visiting step
     */
    @Override
    public void visit(Step step) {
        if (step != null) {
            stepCounter++;
            logger.debug(String.format("Found new step no. %d: %s.", 
            				stepCounter, step.getContent()));
        } else {
            logger.debug("Visited null value.");
        }
    }

    public Integer getCounter() { return stepCounter; }

    /**
     * Method resetting counter.
     */
    public void resetCounter() { stepCounter = 0; }
}
