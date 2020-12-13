package pl.put.poznan.scenarioqualitychecker.logic.models;

import pl.put.poznan.scenarioqualitychecker.logic.Acceptor;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

import java.util.ArrayList;

public class Step implements Acceptor {
    private String text;
    private int number;
    
    public ArrayList<Step> getSteps() {
        return new ArrayList<Step>();
    }
    
    public Step(String text) {
        this.text = text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
    
}
