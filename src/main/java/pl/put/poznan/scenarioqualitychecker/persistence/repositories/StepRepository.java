package pl.put.poznan.scenarioqualitychecker.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.put.poznan.scenarioqualitychecker.logic.models.Step;

/**
 * Step repository
 */
@Repository
public interface StepRepository extends JpaRepository<Step, String> {}
