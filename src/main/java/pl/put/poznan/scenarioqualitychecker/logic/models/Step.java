package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;

public class Step{
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
}
