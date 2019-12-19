package com.dotslashme.recipe.services;

import com.dotslashme.recipe.entities.Ingredient;
import com.dotslashme.recipe.repositories.IngredientRepository;
import com.dotslashme.recipe.serializations.IngredientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IngredientService {

  private final IngredientRepository repository;
  private final ModelMapper modelMapper;

  @Autowired
  public IngredientService(IngredientRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  public String createIngredient(IngredientDto ingredient) {
    Ingredient i = this.repository.save(this.modelMapper.map(ingredient, Ingredient.class));
    return String.format("/ingredient/%s", i.getId());
  }

  public List<IngredientDto> readIngredients() {
    return this.repository.findAll()
      .stream()
      .map(ingredient -> this.modelMapper.map(ingredient, IngredientDto.class))
      .collect(Collectors.toList());
  }

  public IngredientDto readIngredient(UUID identifier) {
    return this.modelMapper.map(this.repository.getOne(identifier), IngredientDto.class);
  }

  public void deleteIngredient(UUID identifier) {
    this.repository.deleteById(identifier);
  }
}
