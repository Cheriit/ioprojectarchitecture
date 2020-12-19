package pl.put.poznan.scenarioqualitychecker.rest.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.put.poznan.scenarioqualitychecker.logic.models.Actor;
import pl.put.poznan.scenarioqualitychecker.logic.models.Header;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.ActorRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.MainScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.ScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.StepRepository;
import pl.put.poznan.scenarioqualitychecker.visitors.ScenarioStepCounterVisitor;

/**
 * This service handles the basic CRUD operations on Scenarios
 */
@Service
@Transactional(readOnly = true)
public class MainScenarioService {
	
	MainScenarioRepository mainScenarioRepository;
	ScenarioRepository scenarioRepository;
	StepRepository stepRepository;
	ActorRepository actorRepository;
	
	@Autowired
	public MainScenarioService(MainScenarioRepository mainScenarioRepository,
			ScenarioRepository scenarioRepository, StepRepository stepRepository, 
			ActorRepository actorRepository) {
		this.mainScenarioRepository = mainScenarioRepository;
		this.scenarioRepository = scenarioRepository;
		this.stepRepository = stepRepository;
		this.actorRepository = actorRepository;
	}
	
	Logger logger = LoggerFactory.getLogger(MainScenarioService.class);
	
	/**
	 * Fetches the main scenario.
	 * @param id scenario id
	 * @return scenario
	 */
	public Optional<MainScenario> findById(String id) {
		return mainScenarioRepository.findById(id);
	}
	
	/**
	 * Saves a new scenario in the database.
	 * @param scenario to save
	 */
	@Transactional(readOnly = false)
	public void create(MainScenario scenario) {
		mainScenarioRepository.save(scenario);
		logger.debug("Scenario created.");
	}
	
	/**
	 * Deletes a scenario from the database.
	 * @param scenario to delete
	 */
	@Transactional(readOnly = false)
	public void delete(MainScenario scenario) {
		mainScenarioRepository.delete(scenario);
		logger.debug("Scenario deleted.");
	}
	
	/**
	 * Updates a scenario.
	 * @param oldScenario current scenario
	 * @param newScenario updated scenario
	 */
	@Transactional(readOnly = false)
	public void update(MainScenario oldScenario, MainScenario newScenario) {
		updateHeader(oldScenario.getHead(), newScenario.getHead());
		updateScenario(oldScenario, newScenario);
		logger.debug("Scenario updated.");
	}
	
	private void removeOldActors(List<Actor> actors) {
		List<Actor> toDelete = new ArrayList<>();
		toDelete.addAll(actors);
		actors.clear();
		toDelete.forEach(actor -> actorRepository.delete(actor));
	}
	
	private void addNewActors(List<Actor> actors, List<Actor> toAdd) {
		for(Actor actor : toAdd) {
			Actor newActor = actorRepository.save(actor);
			actors.add(newActor);
		}
	}
	
	/**
	 * Updates a header of a scenario.
	 * @param oldHeader current header
	 * @param newHeader updated header
	 */
	@Transactional(readOnly = false)
	public void updateHeader(Header oldHeader, Header newHeader) {
		if(!oldHeader.getTitle().equals(newHeader.getTitle()))
			oldHeader.setTitle(newHeader.getTitle());
		
		removeOldActors(oldHeader.getActors());
		removeOldActors(oldHeader.getSystemActors());

		addNewActors(oldHeader.getActors(), newHeader.getActors());
		addNewActors(oldHeader.getSystemActors(), newHeader.getSystemActors());
	}
	
	/**
	 * Updates a specific scenario.
	 * @param oldScenario current scenario
	 * @param newScenario updated scenario
	 */
	@Transactional(readOnly = false)
	public void updateScenario(Scenario oldScenario, Scenario newScenario) {
		if(!oldScenario.getContent().equals(newScenario.getContent()))
			oldScenario.setContent(newScenario.getContent());
		if(oldScenario.getNumber() != newScenario.getNumber())
			oldScenario.setNumber(newScenario.getNumber());
		
		List<Step> toDelete = new ArrayList<>();
		toDelete.addAll(oldScenario.getSteps());
		oldScenario.getSteps().clear();
		
		for(Step step : toDelete) {
			if(step instanceof Scenario) {
				scenarioRepository.delete((Scenario) step);
			} else {
				stepRepository.delete(step);
			}
		}
		
		for(Step step : newScenario.getSteps()) {
			Step newStep = null;
			if(step instanceof Scenario) {
				newStep = scenarioRepository.save((Scenario)step);
			} else {
				newStep = stepRepository.save(step);
			}
			oldScenario.addStep(newStep);
		}
	}

	/**
	 * Fetches all scenarios from repository.
	 * @return all scenarios
	 */
	public List<MainScenario> findAll() {
		return mainScenarioRepository.findAll();
	}

	/**
	 * Fetches the main scenario.
	 * @param scenario scenario
	 * @return scenario's stepCount
	 */
	public Integer getScenarioStepCount(Scenario scenario) {
		ScenarioStepCounterVisitor visitor = new ScenarioStepCounterVisitor();
		scenario.accept(visitor);
		return visitor.getCounter();
	}
	/**
	 * Returns requested scenario without nodes over depth limit
	 * @param scenario Scenario
	 * @param depth maximum node depth(main scenario is zero)
	 * @return MainScenario
	 */
	public MainScenario getScenarioLimitedByDepth(MainScenario scenario, int depth) {
			return (MainScenario)scenario.getLimitedDepthCopy(depth);
	}
}
