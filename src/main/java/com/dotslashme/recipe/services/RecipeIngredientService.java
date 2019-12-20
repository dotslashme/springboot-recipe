package com.dotslashme.recipe.services;

import com.dotslashme.recipe.entities.RecipeIngredient;
import com.dotslashme.recipe.repositories.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

  private final RecipeIngredientRepository repository;
  private final IngredientService ingredientService;
  private final MeasurementService measurementService;

  @Autowired
  public RecipeIngredientService(RecipeIngredientRepository repository, IngredientService ingredientService, MeasurementService measurementService) {
    this.repository = repository;
    this.ingredientService = ingredientService;
    this.measurementService = measurementService;
  }

  public RecipeIngredient createRecipeIngredient(RecipeIngredient recipeIngredient) {

    recipeIngredient
      .withMeasurement(this.measurementService.saveMeasurement(recipeIngredient.getMeasurement()))
      .withIngredient(this.ingredientService.saveIngredient(recipeIngredient.getIngredient()));

    return this.repository.save(recipeIngredient);
  }

  public List<RecipeIngredient> createRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
    return recipeIngredients
      .stream()
      .map(this::createRecipeIngredient)
      .collect(Collectors.toList());
  }

  public List<RecipeIngredient> readRecipeIngredients() {
    return this.repository.findAll();
  }

  public void deleteRecipeIngredient(RecipeIngredient recipeIngredient) {
    this.repository.delete(recipeIngredient);
  }

  public void deleteRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
    recipeIngredients
      .forEach(this::deleteRecipeIngredient);
  }
}
