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
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.MainScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.visitors.ScenarioStepCounterVisitor;

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
		logger.debug("Creating scenario...");
	}
	
	/**
	 * Deletes a scenario from the database.
	 * @param scenario to delete
	 */
	@Transactional(readOnly = false)
	public void delete(MainScenario scenario) {
		mainScenarioRepository.delete(scenario);
		logger.debug("Deleting scenario...");
	}
	
	/**
	 * Updates a scenario.
	 * @param oldScenario current scenario
	 * @param newScenario updated scenario
	 */
	@Transactional(readOnly = false)
	public void update(MainScenario oldScenario, MainScenario newScenario) {
		//TODO: Implement
		logger.debug("Updating scenario...");
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
	public MainScenario getScenarioLimitedByDepth(MainScenario scenario, int depth)
	{
			return (MainScenario)scenario.getLimitedDepthCopy(depth);
	}
}
