package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.models.*;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;
    public TextTransformer(){
        transforms = new String[]{};
    }
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
    public void UseCaseExample()
    {
        MainScenario main = new MainScenario("Dodanie książki");
        Actor a = new Actor("Bibliotekarz",ActorType.external_actor);
        Actor a2 = new Actor("System",ActorType.system_actor);
        main.head.actors.add(a);
        main.head.system_actors.add(a2);

        main.add_step(new Step("Bibliotekarz wybiera..."));
        main.add_step(new Step("Wyświetla się..."));
        main.add_step(new Step("Bibliotekarz podaje..."));
        Scenario s = new Scenario("IF Bibliotekarz...");
        main.add_step(s);
        s.add_step(new Step("Bibliotekarz wybiera..."));
        s.add_step(new Step("System prezentuje..."));

    }
}
