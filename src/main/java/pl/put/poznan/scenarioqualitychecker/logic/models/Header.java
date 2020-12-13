package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Header {
   
	private String title;
    private List<Actor> actors;
    private List<Actor> systemActors;

    public Header() {}
    
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

	@OneToMany
	public List<Actor> getActors() {
		return actors;
	}

	@OneToMany
	public List<Actor> getSystemActors() {
		return systemActors;
	}
}
