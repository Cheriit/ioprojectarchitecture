package pl.put.poznan.scenarioqualitychecker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.put.poznan.scenarioqualitychecker.fixture.MainScenarioTestFixture;
import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.ActorRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.MainScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.ScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.persistence.repositories.StepRepository;
import pl.put.poznan.scenarioqualitychecker.rest.services.MainScenarioService;

public class ServiceTest {
	
	@Mock
	MainScenarioRepository mainScenarioRepository;
	
	@Mock
	ScenarioRepository scenarioRepository;
	
	@Mock
	StepRepository stepRepository;
	
	@Mock
	ActorRepository actorRepository;
	
	@InjectMocks
	MainScenarioService mainScenarioService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAll() {
		List<MainScenario> scenarios = MainScenarioTestFixture.GET_FIXTURE_MAIN_SCENARIOS(3);
		
		when(mainScenarioRepository.findAll()).thenReturn(scenarios);
		
		List<MainScenario> actual = mainScenarioService.findAll();
		
		assertEquals(scenarios, actual);
		
		verify(mainScenarioRepository).findAll();
	}
	
	@Test
	public void create() {
		MainScenario scenario = MainScenarioTestFixture.GET_SCENARIO();
		
		when(mainScenarioRepository.save(scenario)).thenReturn(scenario);
		
		mainScenarioService.create(scenario);
		
		verify(mainScenarioRepository).save(scenario);
	}
	
	@Test
	public void delete() {
		MainScenario scenario = MainScenarioTestFixture.GET_SCENARIO();
		
		doNothing().when(mainScenarioRepository).delete(scenario);
		
		mainScenarioService.delete(scenario);
		
		verify(mainScenarioRepository).delete(scenario);
	}
	
	@Test
	public void findById() {
		Optional<MainScenario> scenario = Optional.of(MainScenarioTestFixture.GET_SCENARIO());
		final String ID = "1234";
		scenario.get().setId(ID);
		
		when(mainScenarioRepository.findById(ID)).thenReturn(scenario);
		
		Optional<MainScenario> actual = mainScenarioService.findById(ID);
		
		assertEquals(scenario, actual);
		verify(mainScenarioRepository).findById(ID);
	}
}
