package pl.put.poznan.scenarioqualitychecker.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.put.poznan.scenarioqualitychecker.logic.models.Actor;

/**
 * Actor repository
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {}
