package com.dotslashme.recipe.serializations.v1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RecipeDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private transient List<RecipeIngredientDto> ingredients = Collections.emptyList();

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

  public List<RecipeIngredientDto> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<RecipeIngredientDto> ingredients) {
    this.ingredients = ingredients;
  }
}
