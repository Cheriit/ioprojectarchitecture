package pl.put.poznan.scenarioqualitychecker.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.put.poznan.scenarioqualitychecker.logic.models.Scenario;

/**
 * Scenario repository
 */
@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, String> {}
