package com.dotslashme.recipe.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class RecipeIngredient {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id = UUID.randomUUID();

  @ManyToOne
  @JoinColumn(name = "measurement_id")
  private Measurement measurement;

  @ManyToOne
  @JoinColumn(name = "ingredient_id")
  private Ingredient ingredient;

  @Column(precision = 8, scale = 2)
  private Float amount;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Measurement getMeasurement() {
    return measurement;
  }

  public void setMeasurement(Measurement measurement) {
    this.measurement = measurement;
  }

  public RecipeIngredient withMeasurement(Measurement measurement) {
    this.measurement = measurement;
    return this;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  public RecipeIngredient withIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
    return this;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public RecipeIngredient withAmount(Float amount) {
    this.amount = amount;
    return this;
  }
}
