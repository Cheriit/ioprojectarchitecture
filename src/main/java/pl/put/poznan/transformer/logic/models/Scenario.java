package pl.put.poznan.transformer.logic.models;

import java.util.ArrayList;

public class Scenario extends Step{
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
    public void add_step(Step _s)
    {
        _s.depth=this.depth+1;
        _s.number=steps.size();
        steps.add(_s);
    }
}
