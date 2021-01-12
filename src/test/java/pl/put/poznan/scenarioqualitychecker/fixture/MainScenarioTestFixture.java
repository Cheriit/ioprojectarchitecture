package pl.put.poznan.scenarioqualitychecker.fixture;

import java.util.ArrayList;
import java.util.List;

import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;

public class MainScenarioTestFixture {
	
	public static List<MainScenario> GET_FIXTURE_MAIN_SCENARIOS(Integer count) {
		List<MainScenario> scenarios = new ArrayList<MainScenario>();
		
		for(int i = 0; i < count; i++) {
			MainScenario scenario = new MainScenario("SCENARIO NO. " + i);
			
			scenarios.add(scenario);
		}
		
		return scenarios;
	}
	
	public static MainScenario GET_SCENARIO() {
		return GET_FIXTURE_MAIN_SCENARIOS(1).get(0);
	}
}
