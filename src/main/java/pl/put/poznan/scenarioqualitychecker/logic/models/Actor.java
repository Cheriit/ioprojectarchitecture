package pl.put.poznan.scenarioqualitychecker.logic.models;

public class Actor {
    private String name;
    private  ActorType actorType;
    
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
}
