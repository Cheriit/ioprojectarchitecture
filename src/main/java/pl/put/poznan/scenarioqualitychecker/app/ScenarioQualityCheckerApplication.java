package pl.put.poznan.scenarioqualitychecker.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.scenarioqualitychecker.rest"})
@EnableJpaRepositories("pl.put.poznan.scenarioqualitychecker.persistence.repositories")
@EntityScan("pl.put.poznan.scenarioqualitychecker.logic.models")
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
