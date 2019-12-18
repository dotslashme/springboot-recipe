package com.dotslashme.recipe.services;

import com.dotslashme.recipe.entities.Recipe;
import com.dotslashme.recipe.repositories.RecipeRepository;
import com.dotslashme.recipe.serializations.RecipeDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

  private final RecipeRepository repository;
  private final ModelMapper modelMapper;
  private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  public RecipeService(RecipeRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  public void createRecipe(RecipeDto recipe) {
    this.repository.save(this.modelMapper.map(recipe, Recipe.class));
  }

  public RecipeDto readRecipe(UUID identifier) {
    return this.modelMapper.map(this.repository.getOne(identifier), RecipeDto.class);
  }

  public void updateRecipe(UUID identifier, RecipeDto recipe) {
    this.logger.debug("Updating {} with data {}", this.repository.findById(identifier), recipe);
  }

  public void deleteRecipe(UUID identifier) {
    Recipe r = this.repository.getOne(identifier);
    this.repository.delete(r);
  }

  public List<RecipeDto> readAllRecipes() {
    return this.repository.findAll()
      .stream()
      .map(recipe -> this.modelMapper.map(recipe, RecipeDto.class))
      .collect(Collectors.toList());
  }
}
