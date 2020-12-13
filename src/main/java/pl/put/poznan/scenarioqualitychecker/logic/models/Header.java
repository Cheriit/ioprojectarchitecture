package pl.put.poznan.scenarioqualitychecker.logic.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Header {
   
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String title;
	
	@OneToMany(targetEntity=Actor.class, cascade=CascadeType.ALL)
    private List<Actor> actors;
	
	@OneToMany(targetEntity=Actor.class, cascade=CascadeType.ALL)
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

	public List<Actor> getActors() {
		return actors;
	}

	public List<Actor> getSystemActors() {
		return systemActors;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
