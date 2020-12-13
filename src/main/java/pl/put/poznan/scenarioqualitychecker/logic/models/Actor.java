package pl.put.poznan.scenarioqualitychecker.logic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actor {

	@Id
	@GeneratedValue
	private String id;
    private String name;
    private ActorType actorType;
    
    public Actor(String name, ActorType actorType) {
        this.name = name;
        this.actorType = actorType;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActorType getActorType() {
		return actorType;
	}

	public void setActorType(ActorType actorType) {
		this.actorType = actorType;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
