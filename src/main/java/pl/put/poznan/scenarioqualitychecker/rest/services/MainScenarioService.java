package pl.put.poznan.scenarioqualitychecker.rest.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.put.poznan.scenarioqualitychecker.logic.TextTransformer;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;

/**
 * This service handles the basic CRUD operations on Scenarios
 */
@Service
@Transactional(readOnly = true)
public class MainScenarioService {
	
	Logger logger = LoggerFactory.getLogger(MainScenarioService.class);
	
	/**
	 * Fetches the main scenario.
	 * @param id scenario id
	 * @return scenario
	 */
	public MainScenario findById(String id) {
		//TODO: implement real repository
		return TextTransformer.useCaseExample();
	}
	
	/**
	 * Saves a new scenario in the database.
	 * @param scenario
	 */
	@Transactional(readOnly = false)
	public void create(MainScenario scenario) {
		logger.debug("Creating scenario...");
	}
	
	/**
	 * Deletes a scenario from the database.
	 * @param scenario
	 */
	@Transactional(readOnly = false)
	public void delete(MainScenario scenario) {
		logger.debug("Deleting scenario...");
	}
	
	/**
	 * Updates a scenario.
	 * @param oldScenario current scenario
	 * @param newScenario updated scenario
	 */
	@Transactional(readOnly = false)
	public void update(MainScenario oldScenario, MainScenario newScenario) {
		logger.debug("Updating scenario...");
	}
}
