package pl.put.poznan.scenarioqualitychecker.logic.models;

import pl.put.poznan.scenarioqualitychecker.logic.Acceptor;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

import java.util.ArrayList;

public class Step implements Acceptor {
    public String text;
    public int number;
    public ArrayList<Step> get_steps()
    {
        return new ArrayList<Step>();
    }
    public Step(String _text)
    {
        text= _text;
    }

    /**
     * Accept new visitor and pass it further in data structure.
     *
     * @param visitor visiting class
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
