package com.dotslashme.recipe.serializations;

import java.util.UUID;

public class RecipeIngredientDto {

  private UUID id;

  private MeasurementDto measurement;

  private IngredientDto ingredient;

  private Float amount;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public MeasurementDto getMeasurement() {
    return measurement;
  }

  public void setMeasurement(MeasurementDto measurement) {
    this.measurement = measurement;
  }

  public IngredientDto getIngredient() {
    return ingredient;
  }

  public void setIngredient(IngredientDto ingredient) {
    this.ingredient = ingredient;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }
}
