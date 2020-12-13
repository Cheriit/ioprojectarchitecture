package pl.put.poznan.scenarioqualitychecker.logic.models;

public class MainScenario extends Scenario {
    private Header head;
    
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
