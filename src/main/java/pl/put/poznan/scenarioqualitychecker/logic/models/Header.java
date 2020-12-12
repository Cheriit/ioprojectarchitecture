package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;

public class Header {
    public String title;
    public ArrayList<Actor>actors;
    public ArrayList<Actor>system_actors;

    public Header(String _title)
    {
        title=_title;
        actors = new ArrayList<Actor>();
        system_actors = new ArrayList<Actor>();
    }
}
