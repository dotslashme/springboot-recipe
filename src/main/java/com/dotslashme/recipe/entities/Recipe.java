package com.dotslashme.recipe.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Recipe implements Serializable {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column(length = 200)
  private String name;

  @OneToMany
  @JoinColumn(name = "recipe_id")
  private List<RecipeIngredient> recipeIngredientList;

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

  public List<RecipeIngredient> getRecipeIngredientList() {
    return recipeIngredientList;
  }

  public void setRecipeIngredientList(List<RecipeIngredient> recipeIngredientList) {
    this.recipeIngredientList = recipeIngredientList;
  }
}
