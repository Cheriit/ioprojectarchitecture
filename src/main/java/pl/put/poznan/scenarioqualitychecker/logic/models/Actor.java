package pl.put.poznan.scenarioqualitychecker.logic.models;

public class Actor {
    public String name;
    public ActorType actor_type;
    public Actor(String _name,ActorType _actor_type)
    {
        name=_name;
        actor_type=_actor_type;
    }
}
