package pl.put.poznan.scenarioqualitychecker.rest.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.put.poznan.scenarioqualitychecker.logic.ActorList;
import pl.put.poznan.scenarioqualitychecker.logic.StepList;
import pl.put.poznan.scenarioqualitychecker.logic.models.Actor;
import pl.put.poznan.scenarioqualitychecker.logic.models.Header;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.ActorRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.MainScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.ScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.StepRepository;

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
		//updateStep(oldScenario, newScenario);
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
	
	@Transactional(readOnly = false)
	public void updateHeader(Header oldHeader, Header newHeader) {
		if(!oldHeader.getTitle().equals(newHeader.getTitle()))
			oldHeader.setTitle(newHeader.getTitle());
		
		removeOldActors(oldHeader.getActors());
		removeOldActors(oldHeader.getSystemActors());

		addNewActors(oldHeader.getActors(), newHeader.getActors());
		addNewActors(oldHeader.getSystemActors(), newHeader.getSystemActors());
		
//		ActorList oldActors = new ActorList(oldHeader.getActors());
//		ActorList newActors = new ActorList(newHeader.getActors());
//		
//		updateActorList(oldActors, newActors);
//		
//		ActorList oldSystemActors = new ActorList(oldHeader.getSystemActors());
//		ActorList newSystemActors = new ActorList(newHeader.getSystemActors());
//		
//		updateActorList(oldSystemActors, newSystemActors);
	}
	
//	@Transactional(readOnly = false)
//	private void updateActorList(ActorList oldActors, ActorList newActors) {
//		for(Actor actor : newActors) {
//			Optional<Actor> oldActor = oldActors.findById(actor.getId());
//		
//			if(oldActor.isPresent()) {
//				updateActor(oldActor.get(), actor);
//			} else {
//				Actor newActor = actorRepository.save(actor);
//				oldActors.add(newActor);
//			}
//		}
//
//		List<Actor> toDelete = new ArrayList<>();
//		for(Actor actor : oldActors) {
//			Optional<Actor> newActor = newActors.findById(actor.getId());
//			if(newActor.isEmpty()) {
//				toDelete.add(actor);
//			}
//		}
//		oldActors.removeAll(toDelete);
//	}
	
//	@Transactional(readOnly = false)
//	public void updateActor(Actor oldActor, Actor newActor) {
//		if(!oldActor.getName().equals(newActor.getName()))
//			oldActor.setName(newActor.getName());
//		if(!oldActor.getActorType().equals(newActor.getActorType()))
//			oldActor.setActorType(newActor.getActorType());
//	}
//	
//	@Transactional(readOnly = false)
//	public void updateStep(Step oldStep, Step newStep) {
//		if(!oldStep.getContent().equals(newStep.getContent()))
//			oldStep.setContent(newStep.getContent());
//		if(oldStep.getNumber() != newStep.getNumber())
//			oldStep.setNumber(newStep.getNumber());
//		
//		if(oldStep instanceof Scenario && newStep instanceof Scenario) {
//			updateScenario((Scenario)oldStep, (Scenario)newStep);
//		}
//	}
	
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
		
//		StepList oldSteps = new StepList(oldScenario.getSteps());
//		StepList newSteps = new StepList(newScenario.getSteps());
//		
//		List<Step> toDelete = new ArrayList<>();
//		for(Step step : newSteps) {
//			Optional<Step> oldStep = oldSteps.findById(step.getId());
//			
//			if(oldStep.isPresent()) {
//				if(oldStep.get() instanceof Scenario && !(step instanceof Scenario)
//						|| !(oldStep.get() instanceof Scenario) && step instanceof Scenario) {
//					toDelete.add(oldStep.get());
//					Step newStep = stepRepository.save(step);
//					oldScenario.addStep(newStep);
//				} else {
//					updateStep(oldStep.get(), step);
//				}
//				
//			} else {
//				Step newStep = stepRepository.save(step);
//				oldScenario.addStep(newStep);
//			}
//		}
//		
//		for(Step step : oldSteps) {
//			Optional<Step> newStep = newSteps.findById(step.getId());
//			
//			if(newStep.isEmpty()) {
//				toDelete.add(step);
//			}
//		}
//		
//		oldSteps.removeAll(toDelete);
	}

	/**
	 * Fetches all scenarios from repository.
	 * @return all scenarios
	 */
	public List<MainScenario> findAll() {
		return mainScenarioRepository.findAll();
	}
}
