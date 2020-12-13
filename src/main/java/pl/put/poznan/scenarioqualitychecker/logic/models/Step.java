package pl.put.poznan.scenarioqualitychecker.logic.models;

import pl.put.poznan.scenarioqualitychecker.logic.Acceptable;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Step implements Acceptable {
	
	@Id
	@GeneratedValue
	private String id;
    private String content;
    private int number;
    
    public Step(String content) {
        this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
