package com.dotslashme.recipe.serializations.v1;

import java.io.Serializable;
import java.util.UUID;

public class IngredientDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private Integer kcalPer100Grams;

  private Float carbohydratesPer100Grams;

  private Float fatsPer100Grams;

  private Float proteinsPer100Grams;

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

  public Integer getKcalPer100Grams() {
    return kcalPer100Grams;
  }

  public void setKcalPer100Grams(Integer kcalPer100Grams) {
    this.kcalPer100Grams = kcalPer100Grams;
  }

  public Float getCarbohydratesPer100Grams() {
    return carbohydratesPer100Grams;
  }

  public void setCarbohydratesPer100Grams(Float carbohydratesPer100Grams) {
    this.carbohydratesPer100Grams = carbohydratesPer100Grams;
  }

  public Float getFatsPer100Grams() {
    return fatsPer100Grams;
  }

  public void setFatsPer100Grams(Float fatsPer100Grams) {
    this.fatsPer100Grams = fatsPer100Grams;
  }

  public Float getProteinsPer100Grams() {
    return proteinsPer100Grams;
  }

  public void setProteinsPer100Grams(Float proteinsPer100Grams) {
    this.proteinsPer100Grams = proteinsPer100Grams;
  }
}
