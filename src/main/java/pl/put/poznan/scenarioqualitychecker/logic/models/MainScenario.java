package pl.put.poznan.scenarioqualitychecker.logic.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;

@Entity
@Inheritance
public class MainScenario extends Scenario {
	
	@OneToOne(targetEntity=Header.class, cascade=CascadeType.ALL)
    private Header head;
    
    public MainScenario() { super(); }
    
    public MainScenario(String name) {
        super(name);
        this.head = new Header(name);
    }

	public Header getHead() {
		return head;
	}

	public void setHead(Header head) {
		this.head = head;
	}
}
