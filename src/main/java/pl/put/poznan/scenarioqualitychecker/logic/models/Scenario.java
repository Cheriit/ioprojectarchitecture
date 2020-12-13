package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;

import javax.persistence.Entity;

import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

@Entity
public class Scenario extends Step {
    protected ArrayList<Step> steps;
    
    public ArrayList<Step> getSteps() {
        return steps;
    }

    public Scenario(String text) {
        super(text);
        this.steps = new ArrayList<Step>();
    }
    
    public void addStep(Step step) {
        step.setNumber(steps.size());
        steps.add(step);
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
