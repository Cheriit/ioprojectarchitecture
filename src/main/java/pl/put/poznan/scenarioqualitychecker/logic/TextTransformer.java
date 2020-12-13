package pl.put.poznan.scenarioqualitychecker.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.scenarioqualitychecker.logic.models.*;
import pl.put.poznan.scenarioqualitychecker.rest.TextTransformerController;
import pl.put.poznan.scenarioqualitychecker.visitors.ScenarioStepCounterVisitor;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transformations;
    Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    
    public TextTransformer() {
        transformations = new String[]{};
    }
    
    public TextTransformer(String[] transformations) {
        this.transformations = transformations;
    }

    public String transform(String text) {
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }

    public void useCaseExample() {
        MainScenario main = new MainScenario("Dodanie książki");
        Actor a = new Actor("Bibliotekarz",ActorType.external_actor);
        Actor a2 = new Actor("System",ActorType.system_actor);
        main.getHead().getActors().add(a);
        main.getHead().getSystemActors().add(a2);
        
        main.addStep(new Step("Bibliotekarz wybiera..."));
        main.addStep(new Step("Wyświetla się..."));
        main.addStep(new Step("Bibliotekarz podaje..."));
        Scenario s = new Scenario("IF Bibliotekarz...");
        main.addStep(s);
        s.addStep(new Step("Bibliotekarz wybiera..."));
        s.addStep(new Step("System prezentuje..."));

        ScenarioStepCounterVisitor counterVisitor = new ScenarioStepCounterVisitor();

        main.accept(counterVisitor);
    }
}
