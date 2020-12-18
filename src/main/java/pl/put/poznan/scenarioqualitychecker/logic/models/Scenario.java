package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import java.util.List;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    /**
     * Return this scenario to defined depth limit
     *
     * @param depthLimit limit od depth in the tree we want to see
     */
    @Override
    public Step getLimitedDepthCopy(int depthLimit) {
        Scenario result = new Scenario(this.getContent());
        result.setId(this.getId());
        result.setNumber(this.getNumber());
        if(depthLimit<0)
        {
            throw new NotImplementedException();
        }
        if (depthLimit>0){
            for(int i=0;i<this.steps.size();i++){
                result.addStep(this.steps.get(i).getLimitedDepthCopy(depthLimit-1));
            }
        }
        return result;
    }
}
