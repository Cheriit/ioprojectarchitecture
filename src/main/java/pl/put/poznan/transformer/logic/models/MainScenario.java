package pl.put.poznan.transformer.logic.models;

import java.util.ArrayList;

public class MainScenario extends Scenario{
    public Header head;
    public  MainScenario(String _name)
    {
        super(_name);
        head = new Header(_name);
        depth = 0;
    }
}
