package com.dotslashme.recipe.repositories;

import com.dotslashme.recipe.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {

  Optional<Measurement> findByName(String name);
}
