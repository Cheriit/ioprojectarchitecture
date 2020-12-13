package pl.put.poznan.scenarioqualitychecker.logic.models;

import pl.put.poznan.scenarioqualitychecker.logic.Acceptor;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

import java.util.ArrayList;

public class Scenario extends Step implements Acceptor {
    protected ArrayList<Step>steps;
    public ArrayList<Step> get_steps()
    {
        return steps;
    }

    public Scenario(String _text)
    {
        super(_text);
        steps = new ArrayList<Step>();
    }
    public void addStep(Step _s)
    {
        _s.number=steps.size();
        steps.add(_s);
    }

    /**
     * Accept new visitor and pass it further in data structure.
     *
     * @param visitor visiting class
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Step step: steps) {
            step.accept(visitor);
        }
    }
}
