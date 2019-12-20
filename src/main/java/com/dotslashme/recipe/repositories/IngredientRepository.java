package com.dotslashme.recipe.repositories;

import com.dotslashme.recipe.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

  Optional<Ingredient> findByName(String name);
}
