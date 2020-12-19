package pl.put.poznan.scenarioqualitychecker.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

/**
 * Extended array list with an improved search method
 */
public class StepList extends ArrayList<Step> {
	
	private static final long serialVersionUID = 1401370421912142803L;

	public StepList(List<Step> steps) {
		super(steps);
	}
	
	public Optional<Step> findById(String id) {
		for(Step step : this) {
			if(step.getId().equals(id))
				return Optional.of(step);
		}
		return Optional.empty();
	}
}
