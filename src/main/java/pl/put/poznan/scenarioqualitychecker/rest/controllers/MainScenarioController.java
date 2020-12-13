package pl.put.poznan.scenarioqualitychecker.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;

@RestController
@RequestMapping("/")
public class MainScenarioController {

	@RequestMapping(value="/{name}", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MainScenario> getScenario(@PathVariable("name") String name) {
		//TODO: implement
		return new ResponseEntity<>(new MainScenario(""), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{name}", method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateScenario(@RequestBody MainScenario scenario) {
		//TODO: implement
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{name}", method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteScenario(@PathVariable("name") String name) {
		//TODO: implement
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> createScenerio(@RequestBody MainScenario scenario) {
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	
}
