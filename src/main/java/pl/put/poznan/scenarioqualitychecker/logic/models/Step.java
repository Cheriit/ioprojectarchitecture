package pl.put.poznan.scenarioqualitychecker.logic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import pl.put.poznan.scenarioqualitychecker.logic.Acceptable;
import pl.put.poznan.scenarioqualitychecker.visitors.Visitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Entity
public class Step implements Acceptable {
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
    private String content;
    private int number;
    
    public Step() {}
    
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

	/**
	 * Return this scenario to defined depth limit
	 *
	 * @param depthLimit limit od depth in the tree we want to see
	 */
	public Step getLimitedDepthCopy(int depthLimit) {
		if (depthLimit>=0){
			return this;
		}
		else
		{
			throw new NotImplementedException();
		}
	}
}
