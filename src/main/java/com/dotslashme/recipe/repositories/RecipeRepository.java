package com.dotslashme.recipe.repositories;

import com.dotslashme.recipe.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}
