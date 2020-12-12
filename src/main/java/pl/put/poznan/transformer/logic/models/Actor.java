package pl.put.poznan.transformer.logic.models;

import java.util.ArrayList;

public class Actor {
    public String name;
    public ActorType actor_type;
    public Actor(String _name,ActorType _actor_type)
    {
        name=_name;
        actor_type=_actor_type;
    }
}
