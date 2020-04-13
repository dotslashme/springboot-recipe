package com.dotslashme.recipe.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Recipe {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id = UUID.randomUUID();

  @Column(length = 200)
  private String name;

  @Column(name = "oven_temperature_celsius")
  private Integer ovenTemperatureCelsius;

  @Column(name = "oven_time_minutes")
  private Integer ovenTimeMinutes;

  @Column(name = "preparation_time_minutes")
  private Integer preparationTimeMinutes;

  @OneToMany(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "recipe_id")
  private List<RecipeIngredient> ingredients;

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

  public Integer getOvenTemperatureCelsius() {
    return ovenTemperatureCelsius;
  }

  public void setOvenTemperatureCelsius(Integer ovenTemperatureCelsius) {
    this.ovenTemperatureCelsius = ovenTemperatureCelsius;
  }

  public Integer getOvenTimeMinutes() {
    return ovenTimeMinutes;
  }

  public void setOvenTimeMinutes(Integer ovenTimeMinutes) {
    this.ovenTimeMinutes = ovenTimeMinutes;
  }

  public Integer getPreparationTimeMinutes() {
    return preparationTimeMinutes;
  }

  public void setPreparationTimeMinutes(Integer preparationTimeMinutes) {
    this.preparationTimeMinutes = preparationTimeMinutes;
  }

  public List<RecipeIngredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<RecipeIngredient> ingredients) {
    this.ingredients = ingredients;
  }
}
