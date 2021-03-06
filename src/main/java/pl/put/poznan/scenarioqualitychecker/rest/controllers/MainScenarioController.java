package pl.put.poznan.scenarioqualitychecker.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.rest.services.MainScenarioService;

/**
 * This controller handles the basic REST requests.
 */
@RestController
@RequestMapping("/")
public class MainScenarioController {
	
	MainScenarioService mainScenarioService;

	@Autowired
	public MainScenarioController(MainScenarioService mainScenarioService) {
		this.mainScenarioService = mainScenarioService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<MainScenario>> getAllScenarios() {
		List<MainScenario> scenarios = mainScenarioService.findAll();
		return new ResponseEntity<>(scenarios, HttpStatus.OK);
	}
	
	/**
	 * Returns requested scenario
	 * @param id Scenario's id
	 * @param depth optional parameter; if set, requested senario will be returned without nodes over a depth limit
	 * @return scenario
	 */
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MainScenario> getScenario(
			@PathVariable("id") String id, @RequestParam Optional<Integer> depth) {
		Optional<MainScenario> mainScenario = mainScenarioService.findById(id);
		
		if(mainScenario.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		MainScenario mainScenarioUnpacked = null;
		if(depth.isPresent()) {
			mainScenarioUnpacked 
				= mainScenarioService.getScenarioLimitedByDepth(mainScenario.get(), depth.get());
		} else {
			mainScenarioUnpacked = mainScenario.get();
		}

		return new ResponseEntity<>(mainScenarioUnpacked, HttpStatus.OK);
	}
	
	/**
	 * Updates requested scenario
	 * @param id scenario's id
	 * @param updatedScenario scenario's body with updated fields
	 * @return status code and message
	 */
	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateScenario(@PathVariable("id") String id, 
			@RequestBody MainScenario updatedScenario) {
		Optional<MainScenario> scenario = mainScenarioService.findById(id);
		if(!scenario.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		mainScenarioService.update(scenario.get(), updatedScenario);
		return new ResponseEntity<>("Scanerio updated.", HttpStatus.OK);
	}
	
	/**
	 * Deletes requested scenario.
	 * @param id scenario's id
	 * @return status code and message
	 */
	@DeleteMapping(value="/{name}",	produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteScenario(@PathVariable("id") String id) {
		Optional<MainScenario> scenario = mainScenarioService.findById(id);
		
		if(!scenario.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		mainScenarioService.delete(scenario.get());
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	/**
	 * Creates a new scenario.
	 * @param scenario scenario's body
	 * @return status code and message
	 */
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> createScenerio(@RequestBody MainScenario scenario) {
		mainScenarioService.create(scenario);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Returns requested scenario's step count
	 * @param id Scenario's id
	 * @return scenario's step count
	 */
	@GetMapping(value="/{id}/stepcount", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Integer> getScenarioStepCount(@PathVariable("id") String id) {
		Optional<MainScenario> mainScenario = mainScenarioService.findById(id);

		if(!mainScenario.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Integer scenarioStepCount = mainScenarioService.getScenarioStepCount(mainScenario.get());
		return new ResponseEntity<>(scenarioStepCount, HttpStatus.OK);
	}
	
	/**
	 * Returns the number of steps starting with a given keyword.
	 * @param id main scenario id
	 * @param keyword keyword to find
	 * @return number of steps
	 */
	@GetMapping(value="/{id}/keywords", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Integer> getScenarioKeywordCount(
			@PathVariable("id") String id, @RequestParam String keyword) {
		Optional<MainScenario> mainScenario = mainScenarioService.findById(id);

		if(!mainScenario.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Integer scenarioKeywordCount = mainScenarioService.getScenarioKeywordCount(mainScenario.get(), keyword);
		return new ResponseEntity<>(scenarioKeywordCount, HttpStatus.OK);
	}
}
