package com.dotslashme.recipe.services;

import com.dotslashme.recipe.entities.Recipe;
import com.dotslashme.recipe.entities.RecipeIngredient;
import com.dotslashme.recipe.repositories.RecipeRepository;
import com.dotslashme.recipe.serializations.v1.RecipeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

  private final RecipeRepository repository;
  private final ModelMapper modelMapper;
  private final RecipeIngredientService recipeIngredientService;

  @Autowired
  public RecipeService(RecipeRepository repository, ModelMapper modelMapper, RecipeIngredientService recipeIngredientService) {
    this.repository = repository;
    this.modelMapper = modelMapper;
    this.recipeIngredientService = recipeIngredientService;
  }

  public String createRecipe(RecipeDto recipe) {

    Recipe r = this.modelMapper.map(recipe, Recipe.class);

    if (recipe.getIngredients() != null) {

      r.setIngredients(this.recipeIngredientService.createRecipeIngredients(
        recipe
          .getIngredients()
          .stream()
          .map(recipeIngredientDto -> this.modelMapper.map(recipeIngredientDto, RecipeIngredient.class))
          .collect(Collectors.toList())));
    }

    r = this.repository.save(r);
    return String.format("/recipe/%s", r.getId());
  }

  public List<RecipeDto> readRecipes() {
    return this.repository.findAll()
      .stream()
      .map(recipe -> this.modelMapper.map(recipe, RecipeDto.class))
      .collect(Collectors.toList());
  }

  public RecipeDto readRecipe(UUID identifier) {
    return this.modelMapper.map(this.repository.getOne(identifier), RecipeDto.class);
  }

  public void deleteRecipe(UUID identifier) {
    this.repository.deleteById(identifier);
  }
}
