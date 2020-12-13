package pl.put.poznan.scenarioqualitychecker.visitors;

import pl.put.poznan.scenarioqualitychecker.logic.models.*;

/**
 * Visitor.java
 * Interface class implementing Visitor Design Pattern, implementing following method.
 */
public interface Visitor {

    /**
     * Method performing actions on visiting Step class instance
     *
     * @param step Currently visiting step
     */
    void visit(Step step);
}
