package pl.put.poznan.scenarioqualitychecker.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.put.poznan.scenarioqualitychecker.logic.models.MainScenario;

/**
 * MainScenerio repository used for the persistence layer.
 */
@Repository
public interface MainScenarioRepository extends JpaRepository<MainScenario, String> {}
