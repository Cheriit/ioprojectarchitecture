package pl.put.poznan.scenarioqualitychecker.rest.services;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;
import pl.put.poznan.scenarioqualitychecker.logic.models.Step;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.MainScenarioRepository;

/**
 * This service handles the basic CRUD operations on Scenarios
 */
@Service
@Transactional(readOnly = true)
public class MainScenarioService {
	
	MainScenarioRepository mainScenarioRepository;

	@Autowired
	public MainScenarioService(MainScenarioRepository mainScenarioRepository) {
		this.mainScenarioRepository = mainScenarioRepository;
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
		mainScenarioRepository.delete(oldScenario);
		mainScenarioRepository.save(newScenario);
		logger.debug("Scenario updated.");
	}
	
	//TODO: If we ever need to make it more efficient, the first thing to do
	//		would be making the update operation more detailed
//	@Transactional(readOnly = false)
//	public void updateStep(Step oldStep, Step newStep) {
//		oldStep.setContent(newStep.getContent());
//		oldStep.setNumber(newStep.getNumber());
//		
//		if(oldStep instanceof Scenario && newStep instanceof Scenario) {
//			Scenario oldScenario = (Scenario) oldStep;
//			Scenario newScenario = (Scenario) newStep;
//			
//			
//		}
//			
//		
//	}

	/**
	 * Fetches all scenarios from repository.
	 * @return all scenarios
	 */
	public List<MainScenario> findAll() {
		return mainScenarioRepository.findAll();
	}
}
