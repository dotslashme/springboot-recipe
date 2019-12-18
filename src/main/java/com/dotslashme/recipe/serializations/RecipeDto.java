package com.dotslashme.recipe.serializations;

import java.util.List;
import java.util.UUID;

public class RecipeDto {

  private UUID id;

  private String name;

  private List<RecipeIngredientDto> recipeIngredientList;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RecipeIngredientDto> getRecipeIngredientList() {
    return recipeIngredientList;
  }

  public void setRecipeIngredientList(List<RecipeIngredientDto> recipeIngredientList) {
    this.recipeIngredientList = recipeIngredientList;
  }
}
