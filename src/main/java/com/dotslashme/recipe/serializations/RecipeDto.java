package com.dotslashme.recipe.serializations;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class RecipeDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private transient List<RecipeIngredientDto> recipes;

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

  public List<RecipeIngredientDto> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<RecipeIngredientDto> recipes) {
    this.recipes = recipes;
  }
}
