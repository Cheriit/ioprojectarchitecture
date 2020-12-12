package pl.put.poznan.scenarioqualitychecker.logic;

import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

/**
 * Acceptor.java
 * Interface class implementing Visitor Design Pattern, implementing following method.
 *
 */
public interface Acceptor {

    /**
     * Method accepting given visitor, passing it further into data structure.
     *
     * @param visitor visiting class
     */
    void accept(Visitor visitor);
}
