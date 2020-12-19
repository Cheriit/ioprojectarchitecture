package pl.put.poznan.scenarioqualitychecker.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pl.put.poznan.scenarioqualitychecker.logic.models.Actor;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

/**
 * Extended array list with an improved search method
 */
public class ActorList extends ArrayList<Actor> {
	
	private static final long serialVersionUID = 896246111805858221L;

	public ActorList(List<Actor> actors)  {
		super(actors);
	}
	
	public Optional<Actor> findById(String id) {
		for(Actor actor : this) {
			if(actor.getId().equals(id))
				return Optional.of(actor);
		}
		return Optional.empty();
	}
}
