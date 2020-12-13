package pl.put.poznan.scenarioqualitychecker.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.scenarioqualitychecker.logic.TextTransformer;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.scenarioqualitychecker.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        TextTransformer t = new TextTransformer();
        t.useCaseExample();
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
