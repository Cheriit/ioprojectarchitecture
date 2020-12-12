package pl.put.poznan.scenarioqualitychecker.logic.models;

public class MainScenario extends Scenario {
    public Header head;
    public  MainScenario(String _name)
    {
        super(_name);
        head = new Header(_name);
    }
}
