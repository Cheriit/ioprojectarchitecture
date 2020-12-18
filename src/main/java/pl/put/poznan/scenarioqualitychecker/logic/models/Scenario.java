package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import java.util.List;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

@Entity
@Inheritance
public class Scenario extends Step {
	
	@OneToMany(targetEntity=Step.class, cascade=CascadeType.ALL)
    protected List<Step> steps;
    
    public List<Step> getSteps() {
        return steps;
    }

    public Scenario() { super(); }
    
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
