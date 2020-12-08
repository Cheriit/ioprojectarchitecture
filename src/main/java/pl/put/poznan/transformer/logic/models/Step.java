package pl.put.poznan.transformer.logic.models;

import java.util.ArrayList;

public class Step{
    public String text;
    public int number;
    public int depth;
    public ArrayList<Step> get_steps()
    {
        return new ArrayList<Step>();
    }
    public Step(String _text)
    {
        text= _text;
    }
}
