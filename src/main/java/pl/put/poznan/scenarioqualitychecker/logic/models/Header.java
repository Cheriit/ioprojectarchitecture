package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private String title;
    private List<Actor> actors;
    private List<Actor> systemActors;

    public Header(String title) {
        this.title = title;
        this.actors = new ArrayList<Actor>();
        this.systemActors = new ArrayList<Actor>();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public List<Actor> getSystemActors() {
		return systemActors;
	}
}
