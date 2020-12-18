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


	/**
	 * Return this scenario to defined depth limit
	 *
	 * @param depthLimit limit od depth in the tree we want to see
	 * @return result Step
	 */
	@Override
	public Step getLimitedDepthCopy(int depthLimit) {
		MainScenario result = new MainScenario(this.getContent());
		result.setId(this.getId());
		result.setNumber(this.getNumber());
		result.setHead(this.head);
		if (depthLimit>0){
			for(int i=0;i<this.steps.size();i++){
				Step child = this.steps.get(i).getLimitedDepthCopy(depthLimit-1);
				result.addStep(child);
			}
		}
		return result;
	}
}
